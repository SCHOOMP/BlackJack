class Shop {
    private Player player;
    private double rewardMultiplier;
    private int upgradeCost;

    public Shop(Player player) {
        this.player = player;
        this.rewardMultiplier = 1.0;
        this.upgradeCost = 100;
    }

    public void displayShop() {
        System.out.println("Welcome to the Shop!");
        System.out.println("Current Reward Multiplier: " + rewardMultiplier);
        System.out.println("Chips Available: " + player.getChips());
        System.out.println("Upgrade Cost: " + upgradeCost + " chips");
    }

    public void attemptUpgrade(String choice) {
        if ("yes".equalsIgnoreCase(choice)) {
            upgradeMultiplier();
        } else {
            System.out.println("You chose not to upgrade.");
        }
    }

    private void upgradeMultiplier() {
        if (player.getChips() >= upgradeCost) {
            player.spendChips(upgradeCost); // Spend chips
            rewardMultiplier += 0.5;
            upgradeCost += 50;
            System.out.println("Upgrade successful!");
            System.out.println("New Reward Multiplier: " + rewardMultiplier);
            System.out.println("New Upgrade Cost: " + upgradeCost);
        } else {
            System.out.println("You do not have enough chips for this upgrade.");
        }
    }

    public double getRewardMultiplier() {
        return rewardMultiplier;
    }
}
