package com.github.retropronghorn.lanscapeutils.NewPlayer;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.List;

public class NewPlayer {

    /**
     * Check if the player is new, if they are send off new server methods.
     *
     * @param player Player class to act on.
     * @param language Configuration options for launguage.
     * @param globalConfig Reference to top level ConfigurationSection.
     */
    public static void runCheck(Player player, ConfigurationSection language, ConfigurationSection globalConfig) {
        boolean isNewPlayer = player.hasPlayedBefore();

        if (!isNewPlayer) {
            if (globalConfig.getBoolean("display_welcome")) {
                displayWelcomeLanguage(player, language);
            }
            if (globalConfig.getBoolean("give_welcome_book")) {
                ItemStack welcomeBook = buildWelcomeBook(language, player);
                player.getInventory().setItem(globalConfig.getInt("welcome_book_slot"), welcomeBook);
            }
        }
    }

    /**
     * Send title to player as well as chat message.
     *
     * @param player Player class to act on.
     * @param language Configuration options for launguage.
     */
    private static void displayWelcomeLanguage(Player player, ConfigurationSection language) {
        player.sendTitle(ChatColor.GREEN + language.getString("title"), language.getString("subtitle"), 10, 70, 10);
        player.sendMessage(ChatColor.GREEN + language.getString("chatmessage"));
    }

    /**
     * Create a welcome book for new users.
     *
     * @param language Configuration options for launguage.
     * @param player Player class to act on.
     */
    private static ItemStack buildWelcomeBook(ConfigurationSection language, Player player) {
        ConfigurationSection bookLanguage = language.getConfigurationSection("welcome_book");

        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta bookMeta = (BookMeta) book.getItemMeta();
        List<BaseComponent[]> pages = bookMeta.spigot().getPages();

        BaseComponent[] page1 = new ComponentBuilder(
                ChatColor.DARK_AQUA + "Welcome " + player.getDisplayName() + "! \n" +
                        "We hope you enjoy your stay. \n\n" +
                        "Please take a moment to join our discord for info as our server updates and grows! \n\n" +
                        ChatColor.LIGHT_PURPLE + "Click to Join Discord")
                .event(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://discord.gg/lanscape"))
                .create();

        BaseComponent[] page2 = new ComponentBuilder(ChatColor.UNDERLINE + "Server Store \n" +
                ChatColor.RESET + " \n" +
                "Our store supports our network and future growth, if you'd like to support us check out the shop! \n\n" +
                ChatColor.LIGHT_PURPLE + "Visit the Store")
                .event(new ClickEvent(ClickEvent.Action.OPEN_URL, "http://store.lanscape.network"))
                .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Go to the server store!").create()))
                .create();

        bookMeta.spigot().addPage(page1);
        bookMeta.spigot().addPage(page2);

        assert bookLanguage != null;
        bookMeta.setTitle(bookLanguage.getString("title"));
        bookMeta.setAuthor(bookLanguage.getString("author"));

        book.setItemMeta(bookMeta);

        return book;
    }
}
