package com.yogu.core.consumer.messageBean.store;

import java.io.Serializable;

import com.yogu.core.consumer.BussinessType;
import com.yogu.mq.impl.aliyun.CommandMQProducer;

/**
 * 修改客服的消息bean
 * @author ten 2016/2/4.
 */
public class ChangeCustomerServiceOfficerBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 旧客服IMID
     */
    private long oldImid;

    /**
     * 新客服的IMID
     */
    private long newImid;

    /**
     * 餐厅ID
     */
    private long storeId;

    ChangeCustomerServiceOfficerBO() {
    }

    public long getOldImid() {
        return oldImid;
    }

    public ChangeCustomerServiceOfficerBO setOldImid(long oldImid) {
        this.oldImid = oldImid;
        return this;
    }

    public long getNewImid() {
        return newImid;
    }

    public ChangeCustomerServiceOfficerBO setNewImid(long newImid) {
        this.newImid = newImid;
        return this;
    }

    public long getStoreId() {
        return storeId;
    }

    public ChangeCustomerServiceOfficerBO setStoreId(long storeId) {
        this.storeId = storeId;
        return this;
    }

    public static ChangeCustomerServiceOfficerBO builder() {
        return new ChangeCustomerServiceOfficerBO();
    }

    public void request() {
        CommandMQProducer.get().sendJSON(BussinessType.CHANGE_CUSTOMER_SERVICE_OFFICER, this);
    }
}
