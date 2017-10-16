package cn.simplemind.test.enums;


/**
 * 
 * @author wuyingdui
 * @date   2017年9月25日 下午3:22:16
 */
public enum FlowStatusEnum {
    UNCOMMIT("unCommit","待报备"),
    APPROVE("approve","报备中"),
    ACHIEVE("achieve","已报备"),
    WITHDRAW("withdraw","撤回"),
    REJECTED("rejected","退回"),
    ;
    
    private String status;
    private String name;
    
    private FlowStatusEnum(String status,String name){
        this.status = status;
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
