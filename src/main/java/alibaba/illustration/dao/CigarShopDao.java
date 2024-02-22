package alibaba.illustration.dao;

import alibaba.illustration.entity.Cigar;
import alibaba.illustration.entity.CigarShop;
import alibaba.illustration.entity.packMsg.CigarShopItem;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface CigarShopDao {
    List<CigarShop> getShops();
    List<CigarShop> getHaveShops(String user_id);
    List<CigarShopItem> getCigarList(String shop_id);
    List<CigarShopItem> getLimitCigarList(String shop_id,double minPrice,double maxPrice);
    CigarShop getShop(String shop_id);
    Cigar getCigar(String cigar_id);
}
