package com.example.userorderproductproject.Service;

import com.example.userorderproductproject.ApiExeption.ApiExeption;
import com.example.userorderproductproject.Model.MyOrder;
import com.example.userorderproductproject.Model.MyUser;
import com.example.userorderproductproject.Model.Product;
import com.example.userorderproductproject.Repsitory.MyUserRepository;
import com.example.userorderproductproject.Repsitory.OrderRepository;
import com.example.userorderproductproject.Repsitory.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
private final OrderRepository orderRepository;
  private final ProductRepository productRepository  ;

    public List<MyOrder> getallOrder(Integer id) {
//
        return orderRepository.findMyOrderByMyUserId(id);
    }
    public void addOrder(Integer userid, MyOrder myOrder, Product product) {
//        Product product1=productRepository.findProductById()
        myOrder.setId(userid);
        // ShouldTo try it
//        myOrder.setTotalPrice(myOrder.getQuantity()* product.getPrice());
        orderRepository.save(myOrder);
    }
    public void updateOrder(MyOrder myOrder,Integer id,Integer userId) {
        MyOrder oldOrder = orderRepository.findMyOrderById(id);
        if (oldOrder == null) {

            throw new ApiExeption("Order Not found");
        }
        if (oldOrder.getMyUser().getId() != myOrder.getId()) {
            throw new ApiExeption("Erorre,Unauthorize");
        }
        oldOrder.setStatus(myOrder.getStatus());
        oldOrder.setQuantity(myOrder.getQuantity());
        oldOrder.setDateReceived(myOrder.getDateReceived());
        oldOrder.setTotalPrice(myOrder.getTotalPrice());

        orderRepository.save(oldOrder);
    }
    public void deleteOrder(Integer userid,Integer orderId) {
        MyOrder myOrder = orderRepository.findMyOrderById(userid);
        if (myOrder.getId() == null) {

            throw new ApiExeption("Order Not found");
        }
        if (myOrder.getStatus() .equals("inProgess")  || myOrder.getStatus() .equals("complete")) {

            throw new ApiExeption("You can NOT delete The order Becouse its Not complete Or In Progress");
        }
        orderRepository.delete(myOrder);
    }
    public void changeStatus(MyOrder myOrder,Integer userId,String status) {
        MyOrder oldOrder = orderRepository.findMyOrderById(userId);
        if (oldOrder == null) {

            throw new ApiExeption("Order Not found");
        }
        if (oldOrder.getMyUser().getId() != myOrder.getId()) {
            throw new ApiExeption("Erorre,Unauthorize");
        }
        oldOrder.setStatus(status);

        orderRepository.save(oldOrder);
    }

}
