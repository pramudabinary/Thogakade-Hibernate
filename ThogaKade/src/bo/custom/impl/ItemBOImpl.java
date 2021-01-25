package bo.custom.impl;

import bo.custom.ItemBo;
import dao.DaoFactory;
import dao.custom.ItemDAO;
import dto.ItemDTO;
import entity.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemBo {

    private ItemDAO dao= DaoFactory.getInstance()
            .getDao(DaoFactory.DAOType.ITEM);

    @Override
    public boolean saveItem(ItemDTO dto) throws Exception {
        return dao.save(new Item(dto.getCode(),
                dto.getDescription(),dto.getPrice(),dto.getQtyOnHand()));
    }

    @Override
    public boolean updateItem(ItemDTO dto) throws Exception {
        return dao.update(new Item(dto.getCode(),
                dto.getDescription(),dto.getPrice(),dto.getQtyOnHand()));
    }

    @Override
    public boolean deleteItem(String id) throws Exception {
        return dao.delete(id);
    }

    @Override
    public ItemDTO getItem(String id) throws Exception {
        Item i=dao.get(id);
        return new ItemDTO(i.getCode(),
                i.getDescription(),i.getPrice(),i.getQtyOnHand());
    }

    @Override
    public ArrayList<ItemDTO> getAllItems() throws Exception {
        List<Item> iList=dao.getAll();
        ArrayList<ItemDTO> dtoList= new ArrayList();
        for (Item i : iList) {
            dtoList.add(new ItemDTO(i.getCode(),
                    i.getDescription(),i.getPrice(),i.getQtyOnHand()));
        }
        return dtoList;
    }
}
