<?php
namespace Edu\Miu\Cs\Cs489appsd\Lab1\Productmgmtapp\Model;

class Product {
    private $productId;
    private $name;
    private $dateSupplied;
    private $quantityInStock;
    private $unitPrice;

    // Default constructor
    public function __construct($productId = null, $name = null, $dateSupplied = null, $quantityInStock = null, $unitPrice = null) {
        $this->productId = $productId;
        $this->name = $name;
        $this->dateSupplied = $dateSupplied;
        $this->quantityInStock = $quantityInStock;
        $this->unitPrice = $unitPrice;
    }

    // Getter methods
    public function getProductId() {
        return $this->productId;
    }

    public function getName() {
        return $this->name;
    }

    public function getDateSupplied() {
        return $this->dateSupplied;
    }

    public function getQuantityInStock() {
        return $this->quantityInStock;
    }

    public function getUnitPrice() {
        return $this->unitPrice;
    }

    // Setter methods
    public function setProductId($productId) {
        $this->productId = $productId;
    }

    public function setName($name) {
        $this->name = $name;
    }

    public function setDateSupplied($dateSupplied) {
        $this->dateSupplied = $dateSupplied;
    }

    public function setQuantityInStock($quantityInStock) {
        $this->quantityInStock = $quantityInStock;
    }

    public function setUnitPrice($unitPrice) {
        $this->unitPrice = $unitPrice;
    }
}