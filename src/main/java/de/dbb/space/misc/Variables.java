package de.dbb.space.misc;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

public class Variables {

	public final TextComponent prefix = Component.text("[").color(NamedTextColor.DARK_GRAY)
									.append(Component.text("SPACE").color(TextColor.color(28, 61, 226)).decorate(TextDecoration.BOLD))
									.append(Component.text("] ").color(NamedTextColor.DARK_GRAY));
	
	public final TextComponent noPlayer = prefix.append(Component.text("Du musst ein Spieler sein!").color(NamedTextColor.RED));
	
	public final TextComponent playerNotExist = prefix.append(Component.text("Der Spieler existiert nicht!").color(NamedTextColor.RED));
	
}
