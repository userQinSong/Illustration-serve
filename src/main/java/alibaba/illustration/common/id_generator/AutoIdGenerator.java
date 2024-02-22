package alibaba.illustration.common.id_generator;

public class AutoIdGenerator extends IdGenerator{
    private String autoStr="";

    public AutoIdGenerator(String autoStr) {
        this.autoStr = autoStr + "_";
    }

    public String getNewId(){
        return this.autoStr + super.getUUID();
    }
}
