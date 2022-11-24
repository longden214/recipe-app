package com.quanglong.recipeapp.model;

public class RecipeRequest {
    private String keyword;
    private int[] listCatId;
    private int authorId;
    private String name;
    private String origin;
    private String ingredient;
    private int minServer;
    private int maxServer;
    private int minTotalViews;
    private int maxTotalViews;
    private int minTotalRating;
    private int maxTotalRating;
    private int minCalories;
    private int maxCalories;
    private int minFat;
    private int maxFat;
    private int minProtein;
    private int maxProtein;
    private int minCarbo;
    private int maxCarbo;
    private int minAvgRating;
    private int maxAvgRating;
    private String cookTime;
    private int status;
    private boolean sortByIdDESC;
    private boolean sortByNameASC;
    private boolean sortByServesASC;
    private boolean sortByServesDESC;
    private boolean sortByTotalViewDESC;
    private boolean sortByAvgRatingDESC;
    private boolean sortByTotalRatingDESC;
    private boolean sortByCaloriesDESC;
    private boolean sortByFatDESC;
    private boolean sortByProteinDESC;
    private boolean sortByCarbo;
    private int pageIndex;
    private int pageSize;
    private int loginUserId;

    public int getLoginUserId() {
        return loginUserId;
    }

    public void setLoginUserId(int loginUserId) {
        this.loginUserId = loginUserId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int[] getListCatId() {
        return listCatId;
    }

    public void setListCatId(int[] listCatId) {
        this.listCatId = listCatId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public int getMinServer() {
        return minServer;
    }

    public void setMinServer(int minServer) {
        this.minServer = minServer;
    }

    public int getMaxServer() {
        return maxServer;
    }

    public void setMaxServer(int maxServer) {
        this.maxServer = maxServer;
    }

    public int getMinTotalViews() {
        return minTotalViews;
    }

    public void setMinTotalViews(int minTotalViews) {
        this.minTotalViews = minTotalViews;
    }

    public int getMaxTotalViews() {
        return maxTotalViews;
    }

    public void setMaxTotalViews(int maxTotalViews) {
        this.maxTotalViews = maxTotalViews;
    }

    public int getMinTotalRating() {
        return minTotalRating;
    }

    public void setMinTotalRating(int minTotalRating) {
        this.minTotalRating = minTotalRating;
    }

    public int getMaxTotalRating() {
        return maxTotalRating;
    }

    public void setMaxTotalRating(int maxTotalRating) {
        this.maxTotalRating = maxTotalRating;
    }

    public int getMinCalories() {
        return minCalories;
    }

    public void setMinCalories(int minCalories) {
        this.minCalories = minCalories;
    }

    public int getMaxCalories() {
        return maxCalories;
    }

    public void setMaxCalories(int maxCalories) {
        this.maxCalories = maxCalories;
    }

    public int getMinFat() {
        return minFat;
    }

    public void setMinFat(int minFat) {
        this.minFat = minFat;
    }

    public int getMaxFat() {
        return maxFat;
    }

    public void setMaxFat(int maxFat) {
        this.maxFat = maxFat;
    }

    public int getMinProtein() {
        return minProtein;
    }

    public void setMinProtein(int minProtein) {
        this.minProtein = minProtein;
    }

    public int getMaxProtein() {
        return maxProtein;
    }

    public void setMaxProtein(int maxProtein) {
        this.maxProtein = maxProtein;
    }

    public int getMinCarbo() {
        return minCarbo;
    }

    public void setMinCarbo(int minCarbo) {
        this.minCarbo = minCarbo;
    }

    public int getMaxCarbo() {
        return maxCarbo;
    }

    public void setMaxCarbo(int maxCarbo) {
        this.maxCarbo = maxCarbo;
    }

    public int getMinAvgRating() {
        return minAvgRating;
    }

    public void setMinAvgRating(int minAvgRating) {
        this.minAvgRating = minAvgRating;
    }

    public int getMaxAvgRating() {
        return maxAvgRating;
    }

    public void setMaxAvgRating(int maxAvgRating) {
        this.maxAvgRating = maxAvgRating;
    }

    public String getCookTime() {
        return cookTime;
    }

    public void setCookTime(String cookTime) {
        this.cookTime = cookTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isSortByIdDESC() {
        return sortByIdDESC;
    }

    public void setSortByIdDESC(boolean sortByIdDESC) {
        this.sortByIdDESC = sortByIdDESC;
    }

    public boolean isSortByNameASC() {
        return sortByNameASC;
    }

    public void setSortByNameASC(boolean sortByNameASC) {
        this.sortByNameASC = sortByNameASC;
    }

    public boolean isSortByServesASC() {
        return sortByServesASC;
    }

    public void setSortByServesASC(boolean sortByServesASC) {
        this.sortByServesASC = sortByServesASC;
    }

    public boolean isSortByServesDESC() {
        return sortByServesDESC;
    }

    public void setSortByServesDESC(boolean sortByServesDESC) {
        this.sortByServesDESC = sortByServesDESC;
    }

    public boolean isSortByTotalViewDESC() {
        return sortByTotalViewDESC;
    }

    public void setSortByTotalViewDESC(boolean sortByTotalViewDESC) {
        this.sortByTotalViewDESC = sortByTotalViewDESC;
    }

    public boolean isSortByAvgRatingDESC() {
        return sortByAvgRatingDESC;
    }

    public void setSortByAvgRatingDESC(boolean sortByAvgRatingDESC) {
        this.sortByAvgRatingDESC = sortByAvgRatingDESC;
    }

    public boolean isSortByTotalRatingDESC() {
        return sortByTotalRatingDESC;
    }

    public void setSortByTotalRatingDESC(boolean sortByTotalRatingDESC) {
        this.sortByTotalRatingDESC = sortByTotalRatingDESC;
    }

    public boolean isSortByCaloriesDESC() {
        return sortByCaloriesDESC;
    }

    public void setSortByCaloriesDESC(boolean sortByCaloriesDESC) {
        this.sortByCaloriesDESC = sortByCaloriesDESC;
    }

    public boolean isSortByFatDESC() {
        return sortByFatDESC;
    }

    public void setSortByFatDESC(boolean sortByFatDESC) {
        this.sortByFatDESC = sortByFatDESC;
    }

    public boolean isSortByProteinDESC() {
        return sortByProteinDESC;
    }

    public void setSortByProteinDESC(boolean sortByProteinDESC) {
        this.sortByProteinDESC = sortByProteinDESC;
    }

    public boolean isSortByCarbo() {
        return sortByCarbo;
    }

    public void setSortByCarbo(boolean sortByCarbo) {
        this.sortByCarbo = sortByCarbo;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public RecipeRequest(String keyword, int[] listCatId, int authorId, String name, String origin, String ingredient, int minServer, int maxServer, int minTotalViews, int maxTotalViews, int minTotalRating, int maxTotalRating, int minCalories, int maxCalories, int minFat, int maxFat, int minProtein, int maxProtein, int minCarbo, int maxCarbo, int minAvgRating, int maxAvgRating, String cookTime, int status, boolean sortByIdDESC, boolean sortByNameASC, boolean sortByServesASC, boolean sortByServesDESC, boolean sortByTotalViewDESC, boolean sortByAvgRatingDESC, boolean sortByTotalRatingDESC, boolean sortByCaloriesDESC, boolean sortByFatDESC, boolean sortByProteinDESC, boolean sortByCarbo, int pageIndex, int pageSize, int loginUserId) {
        this.keyword = keyword;
        this.listCatId = listCatId;
        this.authorId = authorId;
        this.name = name;
        this.origin = origin;
        this.ingredient = ingredient;
        this.minServer = minServer;
        this.maxServer = maxServer;
        this.minTotalViews = minTotalViews;
        this.maxTotalViews = maxTotalViews;
        this.minTotalRating = minTotalRating;
        this.maxTotalRating = maxTotalRating;
        this.minCalories = minCalories;
        this.maxCalories = maxCalories;
        this.minFat = minFat;
        this.maxFat = maxFat;
        this.minProtein = minProtein;
        this.maxProtein = maxProtein;
        this.minCarbo = minCarbo;
        this.maxCarbo = maxCarbo;
        this.minAvgRating = minAvgRating;
        this.maxAvgRating = maxAvgRating;
        this.cookTime = cookTime;
        this.status = status;
        this.sortByIdDESC = sortByIdDESC;
        this.sortByNameASC = sortByNameASC;
        this.sortByServesASC = sortByServesASC;
        this.sortByServesDESC = sortByServesDESC;
        this.sortByTotalViewDESC = sortByTotalViewDESC;
        this.sortByAvgRatingDESC = sortByAvgRatingDESC;
        this.sortByTotalRatingDESC = sortByTotalRatingDESC;
        this.sortByCaloriesDESC = sortByCaloriesDESC;
        this.sortByFatDESC = sortByFatDESC;
        this.sortByProteinDESC = sortByProteinDESC;
        this.sortByCarbo = sortByCarbo;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.loginUserId = loginUserId;
    }

    public RecipeRequest() {
    }
}
