/**
 * 
 */
package com.yogu.commons.api.accept.json;

import java.util.LinkedList;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.json.JsonValue.ValueType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.commons.api.ApiStorage;
import com.yogu.commons.api.ApiResult.ApiResultBuild;
import com.yogu.commons.api.accept.StorageAccept;
import com.yogu.commons.utils.StringUtils;

/**
 * <br>
 * 
 * JFan 2015年2月15日 上午10:27:40
 */
public final class JsonAccept {

    public static JsonObjectAccept isObj() {
        return new JsonObjectAccept();
    }

    public static JsonArrayAccept isArr() {
        return new JsonArrayAccept();
    }

    // ####
    // ##
    // ####

    private static abstract class AbsJsonAccept<R> implements StorageAccept {

        protected static final Logger logger = LoggerFactory.getLogger(AbsJsonAccept.class);

        protected LinkedList<ComParam> cps = new LinkedList<ComParam>();

        protected int exist = 1; // 存在
        protected int isObj = 2; // isObject
        protected int isArray = 3; // isArray
        protected int eq = 4; // 相等
        protected int ne = 5; // 不等于
        protected int gt = 6; // 大于
        protected int ge = 7; // 大于等于
        protected int lt = 8; // 小于
        protected int le = 9; // 小于等于

        protected abstract R getResult(ApiStorage storage);

        /*
         * （非 Javadoc）
         * 
         * @see com.vip.commons.api.accept.StorageAccept#accept(com.vip.commons.api.ApiStorage)
         */
        @Override
        public ApiStorage accept(ApiStorage storage) {
            if (null == storage || StringUtils.isBlank(storage.getResponse()))
                return null;
            R result = getResult(storage);
            if (null == result || null == (result = accept(result)))
                return null;
            return storage;
        }

        /**
         * accept func
         */
        public abstract R accept(R result);

        /**
         * 判定：存在、是obj、是array，是相同的逻辑，提取出来。
         */
        protected boolean eoa_no(int c, JsonValue jv) {
            if (exist == c && ValueType.NULL == jv.getValueType())
                return true;
            if (isObj == c && ValueType.OBJECT != jv.getValueType())
                return true;
            if (isArray == c && ValueType.ARRAY != jv.getValueType())
                return true;

            return false;
        }

        /**
         * 判定：大于、大于等于、小于、小于等于，的逻辑是一样的，提取出来
         */
        protected boolean glTE_no(int c, int v2i, int jvInt) {
            if (gt == c && v2i >= jvInt)
                return true;
            if (ge == c && v2i > jvInt)
                return true;
            if (lt == c && v2i <= jvInt)
                return true;
            if (le == c && v2i < jvInt)
                return true;

            return false;
        }

        //
        //
        //

        protected class ComParam {

            public ComParam(int com, Object value) {
                this(com, value, null);
            }

            public ComParam(int com, Object value1, Object value2) {
                this.com = com;
                this.value1 = value1;
                this.value2 = value2;
            }

            private int com;
            private Object value1;
            private Object value2;

        }

    }

    // ####
    // ## JSON Array
    // ####

    public static class JsonArrayAccept extends AbsJsonAccept<JsonArray> {

        public JsonArrayAccept sizeEq(int size) {
            cps.add(new ComParam(eq, size));
            return this;
        }

        public JsonArrayAccept sizeNe(int size) {
            cps.add(new ComParam(ne, size));
            return this;
        }

        public JsonArrayAccept sizeGt(int size) {
            cps.add(new ComParam(gt, size));
            return this;
        }

        public JsonArrayAccept sizeGe(int size) {
            cps.add(new ComParam(ge, size));
            return this;
        }

        public JsonArrayAccept sizeLt(int size) {
            cps.add(new ComParam(lt, size));
            return this;
        }

        public JsonArrayAccept sizeLe(int size) {
            cps.add(new ComParam(le, size));
            return this;
        }

        public JsonArrayAccept indexIsObj(int index) {
            cps.add(new ComParam(isObj, index));
            return this;
        }

        public JsonArrayAccept indexIsArray(int index) {
            cps.add(new ComParam(isArray, index));
            return this;
        }

        public JsonArrayAccept index(int index, JsonAccept ab) {
            // TODO
            throw new AbstractMethodError("Not implemented.");
            // return this;
        }

        /*
         * （非 Javadoc）
         * 
         * @see com.vip.commons.api.accept.json.JsonAccept.AbsJsonAccept#getResult(com.vip.commons.api.ApiStorage)
         */
        @Override
        protected JsonArray getResult(ApiStorage storage) {
            try {
                return storage.getResult(ApiResultBuild.JSON_ARRAY);
            } catch (Exception e) {
                logger.warn("Abnormal JsonArray; response: {}", storage.getResponse());
            }
            return null;
        }

        /*
         * （非 Javadoc）
         * 
         * @see com.vip.commons.api.accept.json.JsonAccept.AbsJsonAccept#accept(java.lang.Object)
         */
        @Override
        public JsonArray accept(JsonArray array) {
            if (null == array)
                return null;

            if (!cps.isEmpty())
                for (ComParam cp : cps) {
                    int c = cp.com;
                    int v1 = (int) cp.value1;

                    int s = array.size();

                    if (1 <= c && c <= 3) {// eoa 用的是index（0开始），其他用的是size（1开始）
                        if (s <= v1)
                            return null;
                        JsonValue jv = null;
                        try {
                            jv = array.get(v1);
                        } catch (Exception e) {
                            // ignore
                        }
                        if (null == jv)
                            return null;

                        if (eoa_no(c, jv))
                            return null;
                    } else if (eq == c && v1 != s)
                        return null;
                    else if (ne == c && v1 == s)
                        return null;
                    else if (glTE_no(c, v1, s))
                        return null;
                }

            return array;
        }
    }

    // ####
    // ## JSON Object
    // ####

    public static class JsonObjectAccept extends AbsJsonAccept<JsonObject> {

        public JsonObjectAccept attrExist(String attr) {
            cps.add(new ComParam(exist, attr));
            return this;
        }

        public JsonObjectAccept attrEq(String attr, int num) {
            cps.add(new ComParam(eq, attr, num));
            return this;
        }

        public JsonObjectAccept attrNe(String attr, int num) {
            cps.add(new ComParam(ne, attr, num));
            return this;
        }

        public JsonObjectAccept attrGt(String attr, int num) {
            cps.add(new ComParam(gt, attr, num));
            return this;
        }

        public JsonObjectAccept attrGe(String attr, int num) {
            cps.add(new ComParam(ge, attr, num));
            return this;
        }

        public JsonObjectAccept attrLt(String attr, int num) {
            cps.add(new ComParam(lt, attr, num));
            return this;
        }

        public JsonObjectAccept attrLe(String attr, int num) {
            cps.add(new ComParam(le, attr, num));
            return this;
        }

        public JsonObjectAccept attrEq(String attr, String str) {
            cps.add(new ComParam(eq, attr, str));
            return this;
        }

        public JsonObjectAccept attrNe(String attr, String str) {
            cps.add(new ComParam(ne, attr, str));
            return this;
        }

        public JsonObjectAccept attrIsObj(String attr) {
            cps.add(new ComParam(isObj, attr));
            return this;
        }

        public JsonObjectAccept attrIsArray(String attr) {
            cps.add(new ComParam(isArray, attr));
            return this;
        }

        public JsonObjectAccept attr(String attr, JsonAccept ab) {
            // TODO
            throw new AbstractMethodError("Not implemented.");
            // return this;
        }

        /*
         * （非 Javadoc）
         * 
         * @see com.vip.commons.api.accept.json.JsonAccept.AbsJsonAccept#getResult(com.vip.commons.api.ApiStorage)
         */
        @Override
        protected JsonObject getResult(ApiStorage storage) {
            try {
                return storage.getResult(ApiResultBuild.JSON_OBJECT);
            } catch (Exception e) {
                logger.warn("Abnormal JsonObject; response: {}", storage.getResponse());
            }
            return null;
        }

        /*
         * （非 Javadoc）
         * 
         * @see com.vip.commons.api.accept.json.JsonAccept.AbsJsonAccept#accept(java.lang.Object)
         */
        @Override
        public JsonObject accept(JsonObject json) {
            if (null == json)
                return null;

            if (!cps.isEmpty())
                for (ComParam cp : cps) {
                    int c = cp.com;
                    String v1 = (String) cp.value1;

                    JsonValue jv = null;
                    try {
                        jv = json.get(v1);
                    } catch (Exception e) {
                        // ignore
                    }
                    if (null == jv)
                        return null;

                    if (eoa_no(c, jv))
                        return null;
                    // str | int
                    else if (eq == c) {
                        Object v2 = cp.value2;
                        if ((ValueType.NUMBER == jv.getValueType() && !(v2.equals(json.getInt(v1)))) //
                                || (ValueType.STRING == jv.getValueType() && !(v2.equals(json.getString(v1)))))
                            return null;
                    }
                    //
                    else if (ne == c) {
                        Object v2 = cp.value2;
                        if ((ValueType.NUMBER == jv.getValueType() && (v2.equals(json.getInt(v1)))) //
                                || (ValueType.STRING == jv.getValueType() && (v2.equals(json.getString(v1)))))
                            return null;
                    }
                    // only Int
                    else if (6 <= c && c <= 9) {// 6~9
                        if (ValueType.NUMBER != jv.getValueType())// not num
                            return null;
                        int v2i = (int) cp.value2;
                        int jvInt = json.getInt(v1);

                        if (glTE_no(c, v2i, jvInt))
                            return null;
                    }
                }

            return json;
        }

    }

}
