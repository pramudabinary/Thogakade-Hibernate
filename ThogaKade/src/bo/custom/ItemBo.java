package bo.custom;

import dto.ItemDTO;

import java.util.ArrayList;

public interface ItemBo {
    public boolean saveItem(ItemDTO dto)throws Exception;
    public boolean updateItem(ItemDTO dto)throws Exception;
    public boolean deleteItem(String id)throws Exception;
    public ItemDTO getItem(String id)throws Exception;
    public ArrayList<ItemDTO> getAllItems()throws Exception;
}
