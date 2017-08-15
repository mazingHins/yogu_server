package com.mazing.mysql;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 数据库表转换成javaBean对象小工具<br>
 * 1 bean属性按原始数据库字段经过去掉下划线,并大写处理首字母等等.<br>
 * 2 生成的bean带了数据库的字段说明.<br>
 * 3 各位自己可以修改此工具用到项目中去.
 */
public class MyEntityUtils {

	private String mp = "_mp";// 表名以此结尾的，不生成DTO、Service文件
	private boolean daoImpl = false;// 是否生成DAO实现类，false则生成package-info来代替
	private boolean poToString = false;// PO对象是否生成 toString 方法
	private String modelName = "entry";

	private String tablename = "";
	private String[] colnames;
	private LinkedList<String> sqlKeyNames;// 主键SQL列名
	private Map<String, Integer> indexMap;// 表字段（名字）对应的 sqlColNames[] 下标
	private String[] sqlColNames; // SQL列名
	private String[] colTypes;
	private int[] colSizes; // 列名大小
	private int[] colScale; // 列名小数精度
	private boolean importUtil = false;
	private boolean importSql = false;
	private boolean importMath = false;
	// 每列的comment
	private Map<String, String> commentMap;
	// 每列的default value
	private Map<String, String> defaultMap;
	// table的comment
	private String tableComment = "";

	private Connection conn = null;

	private String today = "";

	private String category = "";

	/** 基于哪个package生成代码 */
	private String basePackage = "";

	private String lineSeperator;

	private String tablePrefix = "mz_";

	/**
	 * 最长的列名的长度
	 */
	private int maxColumnNameLength = 0;

	/**
	 * 是否分表
	 */
	private boolean splitTable = false;

	public MyEntityUtils() {
	}

	/**
	 * 构造函数
	 * 
	 * @param conn - 数据库连接
	 * @param basePackage - 基于哪个package生成代码，例如: com.mazing.commons
	 */
	public MyEntityUtils(Connection conn, String tablename, String basePackage) {
		this.conn = conn;
		this.tablename = tablename;
		this.basePackage = basePackage;
		today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		lineSeperator = System.getProperty("line.separator");
		if (StringUtils.isEmpty(lineSeperator)) {
			lineSeperator = "\r\n";
		}

		init();
	}

	public boolean isSplitTable() {
		return splitTable;
	}

	public void setSplitTable(boolean splitTable) {
		this.splitTable = splitTable;
	}

	/**
	 * 得到 create table 语句
	 * 
	 * @param tableName
	 * @throws Exception
	 */
	private void getCreateSql(String tableName) throws Exception {
		tableComment = tableName;

		String sql = "show create table " + tableName;
		PreparedStatement stmt = conn.prepareStatement(sql);
		try {
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				String createSql = rs.getString(2);
				parseCreateSql(createSql);
			}
			rs.close();
		} finally {
			stmt.close();
		}
	}

	/**
	 * 解析创建表的语句，得到每个column的COMMENT
	 * 
	 * @param sql
	 */
	private void parseCreateSql(String sql) {
		String[] sqlArray = sql.split("\n");
		commentMap = new HashMap<>(sqlArray.length + sqlArray.length / 3);
		defaultMap = new HashMap<>(sqlArray.length + sqlArray.length / 3);
		for (String line : sqlArray) {
			if (line.indexOf("CREATE") < 0 && line.indexOf("ENGINE") < 0 && line.indexOf("PRIMARY") < 0) {
				line = line.trim();
				String upper = line.toUpperCase();
				String[] info = line.split(" ");
				String columnName = getJavaName(info[0].replaceAll("`", ""));
				String comment = "";
				int commentPos = 0;
				if ((commentPos = upper.indexOf("COMMENT")) > 0) {
					comment = line.substring(commentPos + "COMMENT".length()).replaceAll(",", "").replaceAll("'", "").trim();
				}
				if (comment.length() == 0) {
					if ("createTime".equals(columnName)) {
						comment = "创建时间";
					} else if ("lastUpdateTime".equals(columnName)) {
						comment = "最后更新时间";
					} else if ("gameId".equals(columnName)) {
						comment = "游戏ID";
					} else if ("platformId".equals(columnName)) {
						comment = "平台ID";
					} else if ("serverId".equals(columnName)) {
						comment = "服务器ID";
					}
				}
				int defPos = upper.indexOf(" DEFAULT ");
				if (defPos >= 0) {
					int nextPos = upper.indexOf(" ", defPos + " DEFAULT ".length());
					if (nextPos < 0) {
						nextPos = upper.indexOf(",", defPos + " DEFAULT ".length());
					}
					if (nextPos > 0) {
						String defValue = upper.substring(defPos + " DEFAULT ".length(), nextPos).trim();
						if (defValue.startsWith("'")) {
							defValue = defValue.substring(1, defValue.length() - 1);
						}
						defaultMap.put(columnName, defValue);
					}
				}
				commentMap.put(columnName, comment);
			} else if (line.indexOf("ENGINE") > 0) {
				String upper = line.toUpperCase();
				int commentPos = 0;
				if ((commentPos = upper.indexOf("COMMENT=")) > 0) {
					tableComment = line.substring(commentPos + "COMMENT=".length()).replaceAll(",", "").replaceAll("'", "").trim();
				}
			}
		}
	}

	/**
	 * 获得具体column的comment
	 * 
	 * @param columnName
	 * @return
	 */
	private String getComment(String columnName) {
		String comment = commentMap.get(columnName);
		return (comment.length() == 0 ? columnName : comment);
	}

	private void init() {
		// 数据连Connection获取,自己想办法就行.
		String strsql = "SELECT * FROM " + tablename + " limit 1";// 读一行记录;
		try {
			getCreateSql(tablename);

			// System.out.println(strsql);
			PreparedStatement pstmt = conn.prepareStatement(strsql);
			pstmt.executeQuery();
			ResultSetMetaData rsmd = pstmt.getMetaData();
			DatabaseMetaData metaData = conn.getMetaData();

			sqlKeyNames = new LinkedList<String>();
			indexMap = new HashMap<String, Integer>();
			ResultSet primaryKeys = metaData.getPrimaryKeys(null, null, tablename);
			if (!primaryKeys.isAfterLast())
				while (primaryKeys.next())
					sqlKeyNames.add(primaryKeys.getString("COLUMN_NAME"));

			int size = rsmd.getColumnCount(); // 共有多少列
			colnames = new String[size];
			colTypes = new String[size];
			colSizes = new int[size];
			colScale = new int[size];
			sqlColNames = new String[size];
			for (int i = 0; i < rsmd.getColumnCount(); i++) {
				category = rsmd.getCatalogName(i + 1);
				String sqlCN = rsmd.getColumnName(i + 1).toLowerCase();
				sqlColNames[i] = sqlCN;
				indexMap.put(sqlCN, i);

				if (sqlCN.length() > maxColumnNameLength) {
					maxColumnNameLength = sqlCN.length();
				}
				colnames[i] = sqlCN;
				colTypes[i] = rsmd.getColumnTypeName(i + 1).toLowerCase();
				colScale[i] = rsmd.getScale(i + 1);
				// System.out.println("name: " + rsmd.getSchemaName(i + 1));
				if ("datetime".equals(colTypes[i])) {
					importUtil = true;
				}
				if ("image".equals(colTypes[i]) || "text".equals(colTypes[i])) {
					importSql = true;
				}
				if (colScale[i] > 0) {
					importMath = true;
				}
				colSizes[i] = rsmd.getPrecision(i + 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param tName - 表名
	 * @param outputPath - 输出文件的路径 各位按自己的
	 */
	public void writePOFile(String outputPath) {
		try {
			String content = parse(colnames, colTypes, colSizes);
			writePO(outputPath, content);
			content = parseDto(colnames, colTypes, colSizes);
			writeDTO(outputPath, content);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 输出mybatis配置文件，只有 insert 和 update 语句，其中 update 只适合一个主键的表
	 * 
	 * @param outputPath - 输出目录
	 * @param keyProperty - 主键列名，对应mybatis的 keyProperty属性
	 * @param useGeneratedKeys - 是否自增ID
	 */
	public void writeMyBatisFile(String outputPath, String keyProperty, boolean useGeneratedKeys) throws Exception {
		String fileName = outputPath + File.separator + "sql" + File.separator + tablename + ".xml";
		File template = new File(MyEntityUtils.class.getResource("").getFile() + "mybatis_template.xml");

		String content = FileUtils.readFileToString(template, "utf-8");
		String namespace = basePackage + ".dao." + getJavaName(initcap(tablename)) + "Dao";
		String poName = basePackage + "." + modelName + "." + getJavaName(initcap(tablename)) + "PO";
		content = StringUtils.replace(content, "${namespace}", namespace);
		String parameterType = (splitTable ? "Map" : poName);
		content = StringUtils.replace(content, "${type}", parameterType);
		content = StringUtils.replace(content, "${useGeneratedKeys}", String.valueOf(useGeneratedKeys));
		if (StringUtils.isEmpty(keyProperty)) {
			keyProperty = "";
		} else {
			keyProperty = "keyProperty=\"" + keyProperty + "\"";
		}
		content = StringUtils.replace(content, "${keyProperty}", keyProperty);
		StringBuilder insertSql = new StringBuilder();
		this.processInsertSQL(insertSql);
		content = StringUtils.replace(content, "${insertSql}", insertSql.toString());

		StringBuilder updateSql = new StringBuilder();
		this.processUpdateSQL(updateSql);
		content = StringUtils.replace(content, "${updateSql}", updateSql.toString());

		String deleteByIdSql = processDeleteByIdSql();
		content = StringUtils.replace(content, "${deleteByIdSql}", deleteByIdSql);

		String getByIdSql = processGetByIdSql();
		content = StringUtils.replace(content, "${getByIdSql}", getByIdSql);

		String listAllSql = "SELECT * FROM " + (splitTable ? "${tableName}" : category + "." + tablename);
		content = StringUtils.replace(content, "${listAllSql}", listAllSql);

		FileUtils.writeStringToFile(new File(fileName), content, "utf-8");

	}

	/**
	 * 写PO文件内容到文件
	 * 
	 * @param path
	 * @param content
	 */
	private void writePO(String path, String content) throws Exception {
		String classBaseName = getJavaName(initcap(tablename));
		String fileName = path + File.separator + modelName + File.separator + classBaseName + "PO.java";
		FileUtils.writeStringToFile(new File(fileName), content, "utf-8");
	}

	/**
	 * 写DTO文件内容到文件
	 */
	private void writeDTO(String path, String content) throws Exception {
		if (tablename.endsWith(mp))
			return;
		String classBaseName = getJavaName(initcap(tablename));
		String fileName = path + File.separator + "dto" + File.separator + classBaseName + ".java";
		FileUtils.writeStringToFile(new File(fileName), content, "utf-8");
	}

	/**
	 * 输出DAO文件，只有 save() 和 update()，可以通过修改模版实现其他的内容
	 * 
	 * @param outputPath - 输出目录
	 * @param author - 类的作者
	 */
	public void writeDaoFile(String outputPath, String author) throws Exception {
		String opDao = outputPath + File.separator + "dao" + File.separator;
		String classBaseName = getJavaName(initcap(tablename));
		String daoPackage = basePackage + ".dao";
		String implPackage = basePackage + ".dao.impl";

		String daoClass = classBaseName + "Dao";
		String daoImplClass = classBaseName + "DaoImpl";
		String dao = daoPackage + "." + daoClass;
		String po = classBaseName + "PO";
		String model = basePackage + "." + modelName + "." + po;
		String daoRepository = daoClass.substring(0, 1).toLowerCase() + daoClass.substring(1);

		StringBuilder pksStr = new StringBuilder();
		int index = 1;
		for (String key : sqlKeyNames) {
			String javaName = getJavaName(key);
			int i = indexMap.get(key);
			String javaType = oracleSqlType2JavaType(colTypes[i], colScale[i], colSizes[i]);
			pksStr.append(1 < index++ ? ", " : "");
			pksStr.append("@Param(\"").append(javaName).append("\") ");
			pksStr.append(javaType).append(" ").append(javaName);
		}

		// 生成 DAO 接口文件
		File daoTemplateFile = new File(MyEntityUtils.class.getResource("").getFile() + "java_dao.template");
		String daoTemplate = FileUtils.readFileToString(daoTemplateFile, "utf-8");
		String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String daoContent = StringUtils.replace(daoTemplate, "${package}", daoPackage);
		daoContent = StringUtils.replace(daoContent, "${model}", model);
		daoContent = StringUtils.replace(daoContent, "${author}", author);
		daoContent = StringUtils.replace(daoContent, "${table}", tablename);
		daoContent = StringUtils.replace(daoContent, "${date}", today);
		daoContent = StringUtils.replace(daoContent, "${daoClass}", daoClass);
		daoContent = StringUtils.replace(daoContent, "${pksStr}", pksStr.toString());
		daoContent = StringUtils.replace(daoContent, "${po}", po);
		String imTheDao = "";
		if (!(daoImpl)) {
			daoContent = StringUtils.replace(daoContent, "${theDao}", "@TheDataDao");
			imTheDao = lineSeperator + "import com.mazing.commons.datasource.annocation.TheDataDao;" + lineSeperator;
		}
		daoContent = StringUtils.replace(daoContent, "${imTheDao}", imTheDao);
		String daoFileName = opDao + daoClass + ".java";
		FileUtils.writeStringToFile(new File(daoFileName), daoContent, "utf-8");

		StringBuilder setKeyParams = new StringBuilder();
		int jndex = 1;
		for (String key : sqlKeyNames) {
			String javaName = getJavaName(key);
			if (1 < jndex++)
				setKeyParams.append(lineSeperator);
			setKeyParams.append("\t\tparams.put(\"").append(javaName).append("\", ").append(javaName).append(");");
		}

		// 生成 DaoImpl 文件
		if (daoImpl) {
			File daoImplTemplateFile = new File(MyEntityUtils.class.getResource("").getFile() + "java_dao_impl.template");
			String daoImplTemplate = FileUtils.readFileToString(daoImplTemplateFile, "utf-8");
			String implContent = StringUtils.replace(daoImplTemplate, "${package}", implPackage);
			implContent = StringUtils.replace(implContent, "${model}", model);
			implContent = StringUtils.replace(implContent, "${author}", author);
			implContent = StringUtils.replace(implContent, "${table}", tablename);
			implContent = StringUtils.replace(implContent, "${date}", today);
			implContent = StringUtils.replace(implContent, "${daoClass}", daoClass);
			implContent = StringUtils.replace(implContent, "${po}", po);
			implContent = StringUtils.replace(implContent, "${dao}", dao);
			implContent = StringUtils.replace(implContent, "${pksStr}", pksStr.toString());
			implContent = StringUtils.replace(implContent, "${setKeyParams}", setKeyParams.toString());
			implContent = StringUtils.replace(implContent, "${daoRepository}", daoRepository);
			implContent = StringUtils.replace(implContent, "${daoImplClass}", daoImplClass);
			String daoImplFileName = opDao + File.separator + "impl" + File.separator + daoImplClass + ".java";
			FileUtils.writeStringToFile(new File(daoImplFileName), implContent, "utf-8");
		}
		// 不生成daoImpl，则生成package-info.java来代替
		else {
			String packInfo = outputPath + File.separator + modelName + File.separator + "package-info.java";
			File f = new File(packInfo);
			if (!(f.exists())) {
				StringBuilder sb = new StringBuilder();
				sb.append("/**").append(lineSeperator);
				sb.append(" * ").append(lineSeperator);
				sb.append(" */").append(lineSeperator);
				sb.append("@com.mazing.commons.datasource.annocation.TheTypeAlias").append(lineSeperator);
				sb.append("package ").append(basePackage).append(".").append(modelName).append(";").append(lineSeperator);
				FileUtils.writeStringToFile(f, sb.toString(), "utf-8");
			}
		}
	}

	public void writeServiceFile(String outputPath, String author) throws Exception {
		if (tablename.endsWith(mp))
			return;
		String opService = outputPath + File.separator + "service" + File.separator;
		String classBaseName = getJavaName(initcap(tablename));
		String servicePackage = basePackage + ".service";
		String implPackage = basePackage + ".service.impl";

		String serviceClass = classBaseName + "Service";
		String serviceImplClass = classBaseName + "ServiceImpl";
		String service = servicePackage + "." + serviceClass;
		String po = classBaseName + "PO";
		String dtoModel = basePackage + ".dto." + classBaseName;
		String model = basePackage + "." + modelName + "." + po;

		StringBuilder pksStr = new StringBuilder();
		StringBuilder pkpStr = new StringBuilder();
		int index = 1;
		for (String key : sqlKeyNames) {
			String javaName = getJavaName(key);
			int i = indexMap.get(key);
			String javaType = oracleSqlType2JavaType(colTypes[i], colScale[i], colSizes[i]);
			pksStr.append(1 < index ? ", " : "").append(javaType).append(" ").append(javaName);
			pkpStr.append(1 < index ? ", " : "").append(javaName);
			index += 1;
		}

		// 生成 Service 接口文件
		File daoTemplateFile = new File(MyEntityUtils.class.getResource("").getFile() + "java_service.template");
		String daoTemplate = FileUtils.readFileToString(daoTemplateFile, "utf-8");
		String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String daoContent = StringUtils.replace(daoTemplate, "${package}", servicePackage);
		daoContent = StringUtils.replace(daoContent, "${dtoModel}", dtoModel);
		daoContent = StringUtils.replace(daoContent, "${model}", model);
		daoContent = StringUtils.replace(daoContent, "${author}", author);
		daoContent = StringUtils.replace(daoContent, "${table}", tablename);
		daoContent = StringUtils.replace(daoContent, "${date}", today);
		daoContent = StringUtils.replace(daoContent, "${serviceClass}", serviceClass);
		daoContent = StringUtils.replace(daoContent, "${pksStr}", pksStr.toString());
		daoContent = StringUtils.replace(daoContent, "${dto}", classBaseName);
		daoContent = StringUtils.replace(daoContent, "${po}", po);
		daoContent = StringUtils.replace(daoContent, "${tableComment}", tableComment);
		String daoFileName = opService + serviceClass + ".java";
		FileUtils.writeStringToFile(new File(daoFileName), daoContent, "utf-8");

		StringBuilder setKeyParams = new StringBuilder();
		int jndex = 1;
		for (String key : sqlKeyNames) {
			String javaName = getJavaName(key);
			if (1 < jndex++)
				setKeyParams.append(lineSeperator);
			setKeyParams.append("\t\tparams.put(\"").append(javaName).append("\", ").append(javaName).append(");");
		}

		// 生成 ServiceImpl 文件
		File daoImplTemplateFile = new File(MyEntityUtils.class.getResource("").getFile() + "java_service_impl.template");
		String daoImplTemplate = FileUtils.readFileToString(daoImplTemplateFile, "utf-8");
		String implContent = StringUtils.replace(daoImplTemplate, "${package}", implPackage);
		implContent = StringUtils.replace(implContent, "${dtoModel}", dtoModel);
		implContent = StringUtils.replace(implContent, "${model}", model);
		implContent = StringUtils.replace(implContent, "${author}", author);
		implContent = StringUtils.replace(implContent, "${table}", tablename);
		implContent = StringUtils.replace(implContent, "${date}", today);
		implContent = StringUtils.replace(implContent, "${serviceClass}", serviceClass);
		implContent = StringUtils.replace(implContent, "${daoClass}", classBaseName + "Dao");
		implContent = StringUtils.replace(implContent, "${dto}", classBaseName);
		implContent = StringUtils.replace(implContent, "${po}", po);
		implContent = StringUtils.replace(implContent, "${dao}", basePackage + ".dao." + classBaseName + "Dao");
		implContent = StringUtils.replace(implContent, "${service}", service);
		implContent = StringUtils.replace(implContent, "${pksStr}", pksStr.toString());
		implContent = StringUtils.replace(implContent, "${pkParamsStr}", pkpStr.toString());
		implContent = StringUtils.replace(implContent, "${setKeyParams}", setKeyParams.toString());
		implContent = StringUtils.replace(implContent, "${daoImplClass}", serviceImplClass);
		String daoImplFileName = opService + File.separator + "impl" + File.separator + serviceImplClass + ".java";
		FileUtils.writeStringToFile(new File(daoImplFileName), implContent, "utf-8");
	}

	/**
	 * 解析处理(生成实体类主体代码)
	 */
	private String parse(String[] colNames, String[] colTypes, int[] colSizes) {
		StringBuilder sb = new StringBuilder(4096);

		if (StringUtils.isNotEmpty(basePackage)) {
			sb.append("package ").append(basePackage).append(".").append(modelName).append(";").append(lineSeperator).append(lineSeperator);
		}
		sb.append("import java.io.Serializable;").append(lineSeperator);
		if (importUtil) {
			sb.append("import java.util.Date;").append(lineSeperator);
		}
		if (importSql) {
			sb.append("import java.sql.*;").append(lineSeperator).append(lineSeperator);
		}
		if (importMath)
			sb.append("import java.math.*;").append(lineSeperator);
		sb.append(lineSeperator);
		if (poToString) {
			sb.append("import org.apache.commons.lang3.builder.ToStringBuilder;").append(lineSeperator);
			sb.append("import org.apache.commons.lang3.builder.ToStringStyle;").append(lineSeperator);
		}

		// 表注释
		processColnames(sb, true);
		sb.append("public class " + getJavaName(initcap(tablename)) + "PO implements Serializable {").append(lineSeperator).append(lineSeperator);
		processAllAttrs(sb, "PO");
		processAllMethod(sb);

		if (poToString) {
			sb.append("\tpublic String toString() {").append(lineSeperator);
			sb.append("\t\treturn ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);").append(lineSeperator);
			sb.append("\t}").append(lineSeperator);
		}
		sb.append("}").append(lineSeperator);
		// System.out.println(sb.toString());
		return sb.toString();

	}

	/**
	 * 解析处理(生成实体类DTO主体代码)
	 */
	private String parseDto(String[] colNames, String[] colTypes, int[] colSizes) {
		StringBuilder sb = new StringBuilder();
		if (StringUtils.isNotEmpty(basePackage))
			sb.append("package ").append(basePackage).append(".dto;").append(lineSeperator).append(lineSeperator);
		sb.append("import java.io.Serializable;").append(lineSeperator);
		if (importUtil)
			sb.append("import java.util.Date;").append(lineSeperator);
		if (importSql)
			sb.append("import java.sql.*;").append(lineSeperator).append(lineSeperator);
		if (importMath)
			sb.append("import java.math.*;").append(lineSeperator);
		sb.append(lineSeperator);
		sb.append("import org.apache.commons.lang3.builder.ToStringBuilder;").append(lineSeperator);
		sb.append("import org.apache.commons.lang3.builder.ToStringStyle;").append(lineSeperator);

		// 表注释
		processColnames(sb, false);
		sb.append("public class " + getJavaName(initcap(tablename)) + " implements Serializable {").append(lineSeperator).append(lineSeperator);
		processAllAttrs(sb, "DTO");
		processAllMethod(sb);

		sb.append("\tpublic String toString() {").append(lineSeperator);
		sb.append("\t\treturn ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);").append(lineSeperator);
		sb.append("\t}").append(lineSeperator).append(lineSeperator);
		sb.append("}").append(lineSeperator);
		return sb.toString();
	}

	/**
	 * 生成INSERT的SQL
	 */
	public void processInsertSQL(StringBuilder sb) {
		sb.append("insert into ");
		if (splitTable) {
			// 分表
			sb.append("${tableName}");
		} else {
			sb.append(category).append(".").append(tablename);
		}
		sb.append(" (");
		for (int i = 0; i < colnames.length; i++) {
			if (i > 0)
				sb.append(",");
			sb.append(sqlColNames[i]);
		}
		sb.append(") values (");
		for (int i = 0; i < colnames.length; i++) {
			if (i > 0)
				sb.append(",");
			sb.append("#{");
			if (splitTable) {
				sb.append("entity.");
			}
			sb.append(colnames[i]).append("}");
		}
		sb.append(")");
	}

	/**
	 * 生成UPDATE的SQL
	 */
	public void processUpdateSQL(StringBuilder sb) {
		sb.append("update ");
		if (splitTable) {
			// 分表
			sb.append("${tableName}");
		} else {
			sb.append(category).append(".").append(tablename);
		}
		sb.append(" set ");
		int n = 1;
		int i = (sqlKeyNames.isEmpty() ? 1 : 0);
		for (; i < colnames.length; i++) {
			String colName = sqlColNames[i];
			if ("createTime".equals(colName) || "create_time".equals(colName) || colName.indexOf("creator") >= 0) {
				// 对create_time, creator 之类不update
				continue;
			}
			if (sqlKeyNames.contains(colName))
				continue;
			if (n > 1) {
				sb.append(",");
			}
			sb.append(colName).append("=#{");
			if (splitTable) {
				sb.append("entity.");
			}
			sb.append(colnames[i]).append("}");
			n++;
		}
		sb.append(" where ");

		if (sqlKeyNames.isEmpty()) {
			System.err.println("没有发现PK，使用第一列作为 “主键”\t" + tablename);
			sqlKeyNames.add(sqlColNames[0]);
		}

		int index = 1;
		for (String key : sqlKeyNames) {
			if (1 < index++)
				sb.append(" and ");
			sb.append(key).append("=#{").append(splitTable ? "entity." : "").append(getJavaName(key)).append("}");
		}
	}

	private String processDeleteByIdSql() {
		// processUpdateSQL() 中已经对 sqlKeyNames 赋值
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM ");
		if (splitTable)// 分表
			sql.append("${tableName}");
		else
			sql.append(category).append(".").append(tablename);
		sql.append(" WHERE ");
		int index = 1;
		for (String key : sqlKeyNames) {
			String name = (daoImpl ? getJavaName(key) : "param" + index);// 如果不生成daoImpl，那么参数名为：param1 param2 ..
			sql.append(1 < index++ ? " AND " : "").append(key).append("=#{").append(name).append("}");
		}
		return sql.toString();
	}

	protected String processGetByIdSql() {
		// processUpdateSQL() 中已经对 sqlKeyNames 赋值
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM ");
		if (splitTable)// 分表
			sql.append("${tableName}");
		else
			sql.append(category).append(".").append(tablename);
		sql.append(" WHERE ");
		int index = 1;
		for (String key : sqlKeyNames) {
			String name = (daoImpl ? getJavaName(key) : "param" + index);// 如果不生成daoImpl，那么参数名为：param1 param2 ..
			sql.append(1 < index++ ? " AND " : "").append(key).append("=#{").append(name).append("}");
		}
		return sql.toString();
	}

	/**
	 * 把空格下划线'_'去掉,同时把下划线后的首字母大写
	 * 
	 * @param dbAttrName
	 * @return
	 */
	private String getJavaName(String dbAttrName) {
		char[] ch = dbAttrName.toCharArray();
		char c = 'a';
		if (ch.length > 3) {
			for (int j = 0; j < ch.length; j++) {
				c = ch[j];
				if (c == '_') {
					if (ch[j + 1] >= 'a' && ch[j + 1] <= 'z') {
						ch[j + 1] = (char) (ch[j + 1] - 32);
					}
				}
			}
		}
		String str = new String(ch).replaceAll("_", "");
		return str;
	}

	private String getSpace(String columnName) {
		int n = maxColumnNameLength - columnName.length() + 4;
		StringBuilder buf = new StringBuilder(n);
		for (int i = 0; i < n; i++) {
			buf.append(" ");
		}
		return buf.toString();
	}

	/**
	 * 处理列名,把空格下划线'_'去掉,同时把下划线后的首字母大写 要是整个列在3个字符及以内,则去掉'_'后,不把"_"后首字母大写. 同时把数据库列名,列类型写到注释中以便查看,
	 * 
	 * @param sb
	 */
	private void processColnames(StringBuilder sb, boolean showComment) {
		sb.append(lineSeperator).append("/**").append(lineSeperator).append(" * ").append(tableComment).append(lineSeperator).append(" * ").append(lineSeperator);
		if (showComment) {
			sb.append(" * <pre>").append(lineSeperator).append(" *     自动生成代码: 表名 " + tablename + ", 日期: " + today + "").append(lineSeperator);
			String colsiz = "";
			for (int i = 0; i < colnames.length; i++) {
				String name = colnames[i];
				boolean pk = sqlKeyNames.contains(name);
				colsiz = colSizes[i] <= 0 ? "" : (colScale[i] <= 0 ? "(" + colSizes[i] + ")" : "(" + colSizes[i] + "," + colScale[i] + ")");
				sb.append(" *     " + name.toLowerCase() + (pk ? " <PK> " : "") + getSpace(name) + colTypes[i].toLowerCase() + colsiz + "").append(lineSeperator);
				colnames[i] = getJavaName(name);
			}
			sb.append(" * </pre>").append(lineSeperator);
		}
		sb.append(" */").append(lineSeperator);
	}

	/**
	 * 生成所有的方法
	 * 
	 * @param sb
	 */
	private void processAllMethod(StringBuilder sb) {
		for (int i = 0; i < colnames.length; i++) {
			sb.append("\tpublic void set" + initcap(colnames[i]) + "(" + oracleSqlType2JavaType(colTypes[i], colScale[i], colSizes[i]) + " " + colnames[i] + ") {").append(
					lineSeperator);
			sb.append("\t\tthis." + colnames[i] + " = " + colnames[i] + ";").append(lineSeperator);
			sb.append("\t}").append(lineSeperator).append(lineSeperator);

			sb.append("\tpublic " + oracleSqlType2JavaType(colTypes[i], colScale[i], colSizes[i]) + " get" + initcap(colnames[i]) + "() {").append(lineSeperator);
			sb.append("\t\treturn " + colnames[i] + ";").append(lineSeperator);
			sb.append("\t}").append(lineSeperator).append(lineSeperator);
		}
	}

	/**
	 * 解析输出属性
	 * 
	 * @return
	 */
	private void processAllAttrs(StringBuilder sb, String comment) {
		String cs = StringUtils.join(colnames);
		String ks = StringUtils.join(sqlKeyNames.toArray());
		String ns = StringUtils.join(sqlColNames);
		String ss = StringUtils.join(colTypes);
		String key = StringUtils.join(basePackage, tablename, cs, ks, ns, ss, comment);

		sb.append("\tprivate static final long serialVersionUID = ").append(Long.MIN_VALUE / 3 - (key.hashCode())).append("L;").append(lineSeperator).append(lineSeperator);
		for (int i = 0; i < colnames.length; i++) {
			String javaType = oracleSqlType2JavaType(colTypes[i], colScale[i], colSizes[i]);
			sb.append("\t/** ").append(getComment(colnames[i])).append(" */").append(lineSeperator);
			sb.append("\tprivate " + javaType + " " + colnames[i] + getDefaultValue(colnames[i], javaType) + ";").append(lineSeperator).append(lineSeperator);
		}
	}

	/**
	 * 获取default值
	 * 
	 * @param col - 列名
	 * @param type - java类型
	 * @return
	 */
	private String getDefaultValue(String col, String type) {
		String value = defaultMap.get(col);
		String s = "";
		if (value != null) {
			boolean n = !"null".equals(value.toLowerCase());
			if ("String".equals(type) && n) {
				s = " = \"" + value + "\"";
			} else if (n) {
				s = " = " + value;
			}
		}
		return s;
	}

	/**
	 * 把输入字符串的首字母改成大写
	 * 
	 * @param str
	 * @return
	 */
	private String initcap(String str) {
		if (str.startsWith(tablePrefix)) {
			str = str.substring(tablePrefix.length());
		}
		char[] ch = str.toCharArray();
		if ((ch[0] >= 'a' && ch[0] <= 'z') || (ch[0] >= '0') && ch[0] <= '9') {
			ch[0] = (char) (ch[0] - 32);
		}
		return new String(ch);
	}

	/**
	 * Oracle
	 * 
	 * @param type
	 * @param scale
	 * @return
	 */
	private String oracleSqlType2JavaType(String sqlType, int scale, int size) {
		String type = sqlType.toLowerCase();
		if (type.equals("integer") || "int".equals(type) || "int unsigned".equals(type) || "mediumint".equals(type)) {
			return "int";
		} else if ("tinyint".equals(type) || "tinyint unsigned".equals(type) || "smallint".equals(type) || "smallint unsigned".equals(type)) {
			return "short";
		} else if (type.equals("long")) {
			return "long";
		} else if (type.equals("float") || type.equals("float precision") || type.equals("double") || type.equals("double precision")) {
			return "double";
		} else if (type.equals("number") || type.equals("decimal") || type.equals("numeric") || type.equals("real")) {
			return scale == 0 ? (size < 10 ? "Integer" : "Long") : "BigDecimal";
		} else if (type.equals("varchar") || type.equals("varchar2") || type.equals("char") || type.equals("nvarchar") || type.equals("nchar")) {
			return "String";
		} else if (type.equals("datetime") || type.equals("date") || type.equals("timestamp")) {
			return "Date";
		} else if (type.equals("bigint") || "bigint unsigned".equals(type)) {
			importMath = true;
			return "long";
		} else if (type.equals("varbinary") || type.equals("longblob")) {
			return "byte[]";
		}

		System.err.println("未知类型：" + type);
		return null;
	}

	public static void run(String un, String ps, String url, String pack, String user, String path_, boolean autoIdl, String... tables) {
		run(false, un, ps, url, pack, user, path_, autoIdl, tables);
	}
	
	public static void run(boolean genDaoImpl, String un, String ps, String url, String pack, String user, String path_, boolean autoIdl, String... tables) {
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, un, ps);

			String path = path_ + File.separator + pack;
			for (String table : tables) {
				MyEntityUtils t = new MyEntityUtils(con, table, pack);
				t.daoImpl = genDaoImpl;
				// 输出PO，第一个参数是表名
				t.writePOFile(path);
				// 输出mybatis配置文件，只有 insert 和 update 的 SQL，其中 update 只适合一个主键的表
				// 第2个参数是主键名，第3个参数表明主键是否自增
				t.writeMyBatisFile(path, "", autoIdl);
				// 输出 Dao 及 DaoImpl，只有 save() 和 update() 函数
				// 第2个参数linyi是作者名
				t.writeDaoFile(path, user);
				t.writeServiceFile(path, user);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (null != con)
				try {
					con.close();
				} catch (SQLException e) {
					// ignore
				}
		}

		System.out.println(pack + "\tDone!");
	}

}
