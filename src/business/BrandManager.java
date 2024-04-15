package business;

import core.Helper;
import dao.BrandDao;
import entity.Brand;
import entity.Model;
import java.util.ArrayList;
import java.util.Iterator;

public class BrandManager {
    private final BrandDao brandDao = new BrandDao();
    private final ModelManager modelManager = new ModelManager();

    public BrandManager() {
    }

    public ArrayList<Object[]> getForTable(int size) {
        ArrayList<Object[]> brandRowList = new ArrayList();
        Iterator var3 = this.findAll().iterator();

        while(var3.hasNext()) {
            Brand brand = (Brand)var3.next();
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i++] = brand.getId();
            rowObject[i++] = brand.getName();
            brandRowList.add(rowObject);
        }

        return brandRowList;
    }

    public ArrayList<Brand> findAll() {
        return this.brandDao.findAll();
    }

    public boolean save(Brand brand) {
        if (brand.getId() != 0) {
            Helper.showMsg("error");
        }

        return this.brandDao.save(brand);
    }

    public Brand getById(int id) {
        return this.brandDao.getById(id);
    }

    public boolean update(Brand brand) {
        if (this.getById(brand.getId()) == null) {
            Helper.showMsg("notFound");
        }

        return this.brandDao.update(brand);
    }

    public boolean delete(int id) {
        if (this.getById(id) == null) {
            Helper.showMsg("" + id + " ID kayıtlı marka bulunamadı");
            return false;
        } else {
            Iterator var2 = this.modelManager.getByListBrandId(id).iterator();

            while(var2.hasNext()) {
                Model model = (Model)var2.next();
                this.modelManager.delete(model.getId());
            }

            return this.brandDao.delete(id);
        }
    }
}