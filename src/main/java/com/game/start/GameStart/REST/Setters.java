package com.game.start.GameStart.REST;

import com.game.start.GameStart.entity.DataCarrier;
import com.game.start.GameStart.entity.Product;
import com.game.start.GameStart.entity.ProductType;
import com.game.start.GameStart.entity.Seller;
import com.game.start.GameStart.jpa.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Setters {
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    DataCarrierRepository dataCarrierRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductTypeRepository productTypeRepository;
    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    SessionRepository sessionRepository;
    @Autowired
    ShopRepository shopRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    WorkerRepository workerRepository;
    @Autowired
    Login sessions;
    @GetMapping("/set/all")
    String setter(){
        Seller seller = sessions.getSeller();
        if(seller==null)return "nie";
        var c1 = dataCarrierRepository.save(new DataCarrier(1L,"DVD","PC"));
        var c2 = dataCarrierRepository.save(new DataCarrier(2L,"DVD","PS5"));
        var p1 = productTypeRepository.save(new ProductType(3L,"GTA V","typ","small opis\nhi"));
        var p2 = productTypeRepository.save(new ProductType(2L,"GTA VI","typ","small opis\nhi"));
        productRepository.save(new Product(0L,c1,seller,p2,1,133));
        productRepository.save(new Product(0L,c2,seller,p2,2,163));
        productRepository.save(new Product(0L,c1,seller,p1,1,133));
        productRepository.save(new Product(0L,c2,seller,p1,2,163));
        return "ok";
    }
}
