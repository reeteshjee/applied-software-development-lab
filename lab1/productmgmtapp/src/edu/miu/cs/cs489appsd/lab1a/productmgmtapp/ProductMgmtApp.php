<?php
namespace Edu\Miu\Cs\Cs489appsd\Lab1a\Productmgmtapp;

require_once __DIR__ . '/../../lab1/productmgmtapp/model/Product.php';

use Edu\Miu\Cs\Cs489appsd\Lab1\Productmgmtapp\Model\Product;

class ProductMgmtApp {
    
    /**
     * Print products in JSON, XML, and CSV formats
     * Sorted by name (ascending) then by unit price (descending)
     */
    public static function printProducts($products) {
        // Sort products by name (ascending) and then by unit price (descending)
        usort($products, function($a, $b) {
            $nameComparison = strcmp($a->getName(), $b->getName());
            if ($nameComparison !== 0) {
                return $nameComparison;
            }
            // If names are equal, sort by unit price descending
            return $b->getUnitPrice() <=> $a->getUnitPrice();
        });

        // Print in JSON format
        self::printJSON($products);
        
        echo "\n" . str_repeat("-", 40) . "\n\n";
        
        // Print in XML format
        self::printXML($products);
        
        echo "\n" . str_repeat("-", 40) . "\n\n";
        
        // Print in CSV format
        self::printCSV($products);
    }

    /**
     * Print products in JSON format
     */
    private static function printJSON($products) {
        echo "Printed in JSON Format\n";
        $jsonArray = [];
        
        foreach ($products as $product) {
            $jsonArray[] = [
                'productId' => $product->getProductId(),
                'name' => $product->getName(),
                'dateSupplied' => $product->getDateSupplied(),
                'quantityInStock' => $product->getQuantityInStock(),
                'unitPrice' => $product->getUnitPrice()
            ];
        }
        
        echo json_encode($jsonArray, JSON_PRETTY_PRINT) . "\n";
    }

    /**
     * Print products in XML format
     */
    private static function printXML($products) {
        echo "Printed in XML Format\n";
        echo "<?xml version=\"1.0\"?>\n";
        echo "<products>\n";
        
        foreach ($products as $product) {
            echo "    <product productId=\"" . $product->getProductId() . "\" " .
                 "name=\"" . $product->getName() . "\" " .
                 "dateSupplied=\"" . $product->getDateSupplied() . "\" " .
                 "quantityInStock=\"" . $product->getQuantityInStock() . "\" " .
                 "unitPrice=\"" . number_format($product->getUnitPrice(), 2) . "\"  />\n";
        }
        
        echo "</products>\n";
    }

    /**
     * Print products in CSV format
     */
    private static function printCSV($products) {
        echo "Printed in Comma-Separated Values (CSV) Format\n";
        
        foreach ($products as $product) {
            echo $product->getProductId() . ", " .
                 $product->getName() . ", " .
                 $product->getDateSupplied() . ", " .
                 $product->getQuantityInStock() . ", " .
                 number_format($product->getUnitPrice(), 2) . "\n";
        }
    }

    /**
     * Main method - Entry point of the application
     */
    public static function main() {
        // Create array of products using company data
        $products = [
            new Product("31288741190182539912", "Banana", "2025-01-24", 124, 0.55),
            new Product("29274582650152771644", "Apple", "2024-12-09", 18, 1.09),
            new Product("91899274600128155167", "Carrot", "2025-03-31", 89, 2.99),
            new Product("31288741190182539913", "Banana", "2025-02-13", 240, 0.65)
        ];

        // Print products in all formats
        self::printProducts($products);
        
        echo "\nProcess finished with exit code 0\n";
    }
}

// Execute the main method
ProductMgmtApp::main();