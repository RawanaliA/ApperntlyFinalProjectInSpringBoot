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
 private final MyUserRepository myUserRepository;

    //Get All user Order
    public List<MyOrder> getAllOrders(){
        return orderRepository.findAll();
    }
    public List<MyOrder> getallOrder(Integer userId) {
//
        return orderRepository.findMyOrderByMyUserId(userId);
    }
    //Calculute The Total Price **The Status Already New in Model
    public void addOrder(Integer userid, MyOrder myOrder){
        Product product1=productRepository.findProductById(myOrder.getProduct().getId());
        MyUser myUser=myUserRepository.findMyUserByid(userid);
        myOrder.setMyUser(myUser);
       myOrder.setTotalPrice(myOrder.getQuantity()* product1.getPrice());
        orderRepository.save(myOrder);
    }
    public void updateOrder(MyOrder myOrder,Integer orderId,Integer userId) {
        MyOrder oldOrder = orderRepository.findMyOrderById(orderId);
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
        MyOrder myOrder = orderRepository.findMyOrderById(orderId);
        if (myOrder == null) {

            throw new ApiExeption("Order Not found");
        }
        //هنا يشيك على الاوردر لليوزر هل هو حقه ولا لا
        if(myOrder.getMyUser().getId()!=userid) {
            throw new ApiExeption("This is Not Your Order");
        }
        if (myOrder.getStatus() .equals("inProgess")  || myOrder.getStatus() .equals("complete")) {

            throw new ApiExeption("You can NOT delete The order Becouse its Not complete Or In Progress");
        }
        orderRepository.delete(myOrder);
    }
    public void changeStatus(Integer orderId,Integer userId,String status) {
        MyOrder oldOrder = orderRepository.findMyOrderById(orderId);
        if (oldOrder == null) {

            throw new ApiExeption("Order Not found");
        }
        if (oldOrder.getMyUser().getId() != userId) {
            throw new ApiExeption("Erorre,Unauthorize");
        }
        oldOrder.setStatus(status);

        orderRepository.save(oldOrder);
    }
    //Get MyOrder byId
    public MyOrder findOrderById(Integer id){
        MyOrder order=orderRepository.findMyOrderById(id);
        if(order==null){
            throw new ApiExeption("Sorry, We Couldn't find the Order your looking for, Try another ID!");
        }
        return order;
    }
}
