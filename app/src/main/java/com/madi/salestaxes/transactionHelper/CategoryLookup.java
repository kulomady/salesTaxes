package com.madi.salestaxes.transactionHelper;

import com.madi.salestaxes.constants.Constants;

import java.util.HashMap;

/**
 * Created by rohmadi on 10/19/15.
 */
public class CategoryLookup {

    private static CategoryLookup instance;
    private static HashMap<String, String> itemCategoriesMap;

    public static CategoryLookup getInstance() {
        if (instance == null) {
            instance = new CategoryLookup();
        }
        return instance;
    }

    private CategoryLookup() {
        itemCategoriesMap = new HashMap<String, String>();
        itemCategoriesMap.put("book", Constants.OFFICE_SUPPLIES);
        itemCategoriesMap.put("books", Constants.OFFICE_SUPPLIES);
        itemCategoriesMap.put("chocolate", Constants.FOOD_PRODUCT);
        itemCategoriesMap.put("chocolates", Constants.FOOD_PRODUCT);
        itemCategoriesMap.put("music", Constants.OTHER);
        itemCategoriesMap.put("perfume", Constants.OTHER);
        itemCategoriesMap.put("pills", Constants.MEDICAL_PRODUCT);

    }

    /**
     * Add new product and it's category to current database
     *
     * @param item
     *            Item: keyword
     * @param category
     *            : Category keyword
     * */
    public void addProduct(String item, String category) {
        itemCategoriesMap.put(item, category);
    }

    /**
     * Remove product from the table, so that next time it is look up, it should
     * default to "Other" category
     *
     * @param productName
     *            : Product to be removed from lookup table
     * */
    public void removeProduct(String productName) {
        if (itemCategoriesMap.containsKey(productName))
            itemCategoriesMap.remove(productName);
    }

    /**
     * Get the category of product with given keyword(s)
     *
     * @param productDescription
     *            keyword(s) with description of product
     * @return category of the product if found in the lookup table, otherwise
     *         the default value
     * */
    public String getCategoryFor(String productDescription) {
        String[] productKeyWords = productDescription.split(" ");
        String category = Constants.OTHER;
        for (int keyWordIndex = 0; keyWordIndex < productKeyWords.length; keyWordIndex++) {
            if (itemCategoriesMap.containsKey(productKeyWords[keyWordIndex])) {
                category = itemCategoriesMap.get(productKeyWords[keyWordIndex]);
            }
        }
        return category;
    }

}