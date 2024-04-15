package dao;
import core.Db;
import entity.Car;
import entity.Car.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class CarDao {
    private Connection con = Db.getInstance();
    private final BrandDao brandDao = new BrandDao();
    private final ModelDao modelDao = new ModelDao();

    public CarDao() {
    }

    public Car getById(int id) {
        Car obj = null;
        String query = "SELECT * FROM public.car WHERE car_id = ?";

        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = this.match(rs);
            }
        } catch (SQLException var6) {
            var6.printStackTrace();
        }

        return obj;
    }

    public ArrayList<Car> findAll() {
        return this.selectByQuery("SELECT * FROM public.car ORDER BY car_id ASC");
    }

    public ArrayList<Car> selectByQuery(String query) {
        ArrayList<Car> cars = new ArrayList();

        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);

            while(rs.next()) {
                cars.add(this.match(rs));
            }
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

        return cars;
    }

    public Car match(ResultSet rs) throws SQLException {
        Car car = new Car();
        car.setId(rs.getInt("car_id"));
        car.setModel_id(rs.getInt("car_model_id"));
        car.setPlate(rs.getString("car_plate"));
        car.setColor(Color.valueOf(rs.getString("car_color")));
        car.setKm(rs.getInt("car_km"));
        car.setModel(this.modelDao.getById(car.getModel_id()));
        return car;
    }

    public boolean update(Car car) {
        String query = "UPDATE public.car SET car_model_id = ? , car_color = ? , car_km = ? , car_plate = ? WHERE car_id = ?";

        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, car.getModel_id());
            pr.setString(2, car.getColor().toString());
            pr.setInt(3, car.getKm());
            pr.setString(4, car.getPlate());
            pr.setInt(5, car.getId());
            return pr.executeUpdate() != -1;
        } catch (SQLException var4) {
            var4.printStackTrace();
            return true;
        }
    }

    public boolean save(Car car) {
        String query = "INSERT INTO public.car (car_model_id,car_color,car_km,car_plate) VALUES (?,?,?,?)";

        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, car.getModel_id());
            pr.setString(2, car.getColor().toString());
            pr.setInt(3, car.getKm());
            pr.setString(4, car.getPlate());
            return pr.executeUpdate() != -1;
        } catch (SQLException var4) {
            var4.printStackTrace();
            return true;
        }
    }

    public boolean delete(int carId) {
        String query = "DELETE FROM public.car WHERE car_id = ?";

        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, carId);
            return pr.executeUpdate() != -1;
        } catch (SQLException var4) {
            var4.printStackTrace();
            return true;
        }
    }
}

