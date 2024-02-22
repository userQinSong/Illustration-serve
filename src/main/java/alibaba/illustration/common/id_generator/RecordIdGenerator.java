package alibaba.illustration.common.id_generator;

public class RecordIdGenerator extends IdGenerator{
    private String record_id = "record";
    public String getNewRecordId(){
        return record_id + super.getUUID();
    }
}
