package com.backendDevelopment.dbRelation.dbrestservice.services;

import com.backendDevelopment.dbRelation.dbrestservice.objects.*;
import com.backendDevelopment.dbRelation.dbrestservice.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;
    private final ItemRepository itemRepository;
    private final PaintRepository paintRepository;

    public Order store(Order order) {
        return orderRepository.save(order);
    }

    public List<Order> getAll(){
        return orderRepository.findAll();
    }

    public void remove(Integer id){
        orderRepository.deleteById(id);
    }

    //update cascaded
    public Order store(Integer id, Order order){
        List<Item> newItems = order.getItems();
        Customer newCustomer = order.getCustomer();
        Address newDelvAddress = order.getAddress();
        Address newCustAddress = newCustomer.getAddress();

        Order entityOrder= orderRepository.findById(id).get();
        Address EntityDelvAddress = addressRepository.findById(entityOrder.getAddress().getId()).get();
        //storing orderDetails
        storingOrderDetails(entityOrder,order);
        //storing delvAddressDetails
        storingAddressDetails(EntityDelvAddress, newDelvAddress);
        Customer entityCustomer = customerRepository.findById(entityOrder.getCustomer().getId()).get();
        Address EntityCustAddress = addressRepository.findById(entityCustomer.getAddress().getId()).get();
        //storing customerDetails
        storingCustomerDetails(entityCustomer,newCustomer);
        //storing custAddressDetails
        storingAddressDetails(EntityCustAddress,newCustAddress);

        for(var item:newItems){
            //storing itemDetails
            Item itemEntity = itemRepository.findById(item.getId()).get();
            storingItemDetails(itemEntity,item);
            //storing paintDetails
            Paint paintEntity = paintRepository.findById(item.getPaint().getId()).get();
            storingPaintDetails(paintEntity,item.getPaint());
        }
        return orderRepository.findById(id).get();
    }


    private void storingOrderDetails(Order existingOrder,Order newOrder){
        existingOrder.setDate(newOrder.getDate());
        existingOrder.setDelivery(newOrder.getDelivery());
        orderRepository.save(existingOrder);
    }
    private void storingCustomerDetails(Customer existingCustomer,Customer newCustomer) {
        existingCustomer.setName(newCustomer.getName());
        existingCustomer.setPhoneNumber(newCustomer.getPhoneNumber());
        customerRepository.save(existingCustomer);
    }
    private void storingAddressDetails(Address existingAddress,Address newAddress) {
        if(newAddress == null){
            addressRepository.deleteById(existingAddress.getId());
        }
        else {
            existingAddress.setLine1(newAddress.getLine1());
            existingAddress.setLine2(newAddress.getLine2());
            existingAddress.setLine3(newAddress.getLine3());
            addressRepository.save(existingAddress);
        }
    }
    private void storingItemDetails(Item existingItem,Item newItem) {
        existingItem.setAmount(newItem.getAmount());
        itemRepository.save(existingItem);
    }
    private void storingPaintDetails(Paint existingPaint,Paint newPaint) {
        existingPaint.setColor(newPaint.getColor());
        existingPaint.setType(newPaint.getType());
        existingPaint.setLitre(newPaint.getLitre());
        paintRepository.save(existingPaint);
    }
}
