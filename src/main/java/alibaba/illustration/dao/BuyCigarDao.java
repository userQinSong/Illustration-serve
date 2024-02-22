package alibaba.illustration.dao;
import alibaba.illustration.entity.packMsg.WillBuyPack;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BuyCigarDao {
    boolean isCanBuy(WillBuyPack pack);
    void doBuy(WillBuyPack pack);
    void doImport(WillBuyPack pack);
}
