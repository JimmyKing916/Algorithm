//package InterviewPrep;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by Epimetheus on 1/22/17.
// */
//public class Amazon2017FullTimeOnsite {
//    public Enum Region {
//        CENTER, EAST, WEST, NORTH, SOUTH
//    }
//
//    public Enum Method {
//        GROUND, EXPRESS
//    }
//
//    public class OrderEntry {
//        String pid;
//        Region destination;
//        int expectedDays;
//        int quantity;
//    }
//
//    public class ProductInventory {
//        String pid;
//        int inventoryQuantity;
//        Region location;
//    }
//
//    public class ShippingCost {
//        Region shipFrom;
//        Region shipTo;
//        Method method;
//        int costPerItem;
//        int days;
//    }
//
//    public class InventoryAPI {
//        // Get all inventory list
//        public List<ProductInventory> getAllInventoryList() {
//            // nothing important
//        }
//
//        // Get Inventory List by pid
//        public List<ProductInventory> getInventoryListByPid(String pid) {
//            // nothing important
//        }
//    }
//
//    public class ShippingCostAPI {
//        public List<ShippingCost> getAllShippingCost() {
//            // nothing important
//        }
//    }
//
//    // milestone_1's output class
//    public class ProductInventoryShippingCost {
//        ProductInventory inventory;
//        List<ShippingCost> costList;
//    }
//
//    public class Taobao {
//        //milestone 1
//        public List<ProductInventoryShippingCost> milestone1(String pid, Region destination) {
//            InventoryAPI inventoryAPI = new InventoryAPI();
//            ShippingCostAPI shipingCostAPI = new ShippingCostAPI();
//            List<ProductInventoryShippingCost> result = new ArrayList<>();
//            // write your code here
//
//            return result;
//        }
//
//        // milestone 2 (return value seems to be void, cannot remember but it doesn't matter)
//        // choose one (minimize unfulfilled orders or minmize late orders)
//        public void milestone2(List<OrderEntry> orderEntryList) {
//            // write your code here
//        }
//
//        // milestone3
//        // minimize the cost of every order (minimize average cost per oder),
//        // and compare to milestone2, which is better and why?
//        public void milestone3(List<OrderEntry> orderEntryList) {
//            // write your code here
//        }
//    }
//}
