    /*
     * Copyright (C) 2016 Pivotal Software, Inc.
     *
     * This program is free software: you can redistribute it and/or modify
     * it under the terms of the GNU General Public License as published by
     * the Free Software Foundation, either version 3 of the License, or
     * (at your option) any later version.
     *
     * This program is distributed in the hope that it will be useful,
     * but WITHOUT ANY WARRANTY; without even the implied warranty of
     * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
     * GNU General Public License for more details.
     *
     * You should have received a copy of the GNU General Public License
     * along with this program.  If not, see <http://www.gnu.org/licenses/>.
     */
    package edu.eci.arsw.myrestaurant.restcontrollers;

    import edu.eci.arsw.myrestaurant.model.Order;
    import edu.eci.arsw.myrestaurant.model.ProductType;
    import edu.eci.arsw.myrestaurant.model.RestaurantProduct;
    import edu.eci.arsw.myrestaurant.services.OrderServicesException;
    import edu.eci.arsw.myrestaurant.services.RestaurantOrderServicesStub;

    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.Hashtable;
    import java.util.Map;
    import java.util.concurrent.ConcurrentHashMap;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.stereotype.Controller;
    import org.springframework.web.bind.annotation.*;

    /**
     *
     * @author hcadavid
     */

    @Controller
    @RequestMapping(name = "/orders")
    public class OrdersAPIController {

        @Autowired
        RestaurantOrderServicesStub restaurant;

        private ArrayList<Integer> tables = new ArrayList<>();
        private HashMap<String, Integer> res = new HashMap<>();
        private ArrayList<HashMap<String, Integer>> ress = new ArrayList<>();
        private int cost;
        private Order order;

        @GetMapping
        public  ArrayList<HashMap<String, Integer>> getallorders() throws OrderServicesException{
            try{
                tables.add(1);
                tables.add(3);

                for (Integer integer : tables) {
                    order = restaurant.getTableOrder(integer);
                    cost = restaurant.calculateTableBill(integer);
                    order.toString();
                    res.put(order.toString(), integer);
                    ress.add(res);
                }
                return ress;
            } catch (OrderServicesException e) {
                throw new OrderServicesException("Error al retornar las ordenes" + e);
            }
        }
    }
