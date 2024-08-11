package shop.product.service;

import org.springframework.stereotype.Service;
import shop.product.dto.ProductDTO;
import shop.product.entity.Product;
import shop.product.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDTO addProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setProductNumber(productDTO.getProductNumber());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());

        product = productRepository.save(product);

        productDTO.setId(product.getId());
        return productDTO;
    }

    public ProductDTO getProduct(String id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return null;
        }
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setProductNumber(product.getProductNumber());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        return productDTO;
    }
}
