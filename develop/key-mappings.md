---
title: Key Mappings
description: Creating key mappings and reacting to them.
authors:
  - cassiancc
  - dicedpixels
  - NotNightSky
resources:
  https://upload.wikimedia.org/wikipedia/commons/d/da/KB_United_States.svg: Standard US Keyboard Layout
---

Minecraft handles user input from peripherals such as the keyboard and mouse using key mappings.
Many of these key mappings can be configured through the settings menu.

With help of Fabric API, you can create your own custom key mappings and react to them in your mod.

Key mappings only exist on the client side. This means that registration and reacting to key
mappings should be done on the client side. You can use the **client initializer** for this.

## Creating a Key Mapping {#creating-a-key-mapping}

A key mapping consists of two parts: the mapping to a key, and the category it belongs to.

Let's start with creating a category. A category defines a group of key mappings that will be shown
together in the settings menu.

<<< @/reference/latest/src/client/java/com/example/docs/keymapping/ExampleModKeyMappingsClient.java#category

Next, we can create a key mapping. We will be using Fabric API's `KeyMappingHelper` to register
our key mapping at the same time.

<<< @/reference/latest/src/client/java/com/example/docs/keymapping/ExampleModKeyMappingsClient.java#key_mapping

::: info

Note that the names of the key tokens (`InputConstants.KEY_*`) assume
a [standard US layout](https://upload.wikimedia.org/wikipedia/commons/d/da/KB_United_States.svg).

This means that if you're using an AZERTY layout, pressing on <kbd>A</kbd> would yield
`InputConstants.KEY_Q`.

:::

Sticky keys can also be created with `KeyMappingHelper` by passing a `ToggleKeyMapping` instance
instead of a `KeyMapping`.

Once registered, you can find your key mappings in _Options_ > _Controls_ > _Key Binds_.

![Untranslated Key Category and Mapping](/assets/develop/key-mappings/untranslated.png)

## Translations {#translations}

You'll need to provide translations for both the key mapping and the category.

Category name translation key takes the form of `key.category.<namespace>.<path>`. The key mapping
translation key will be the one you provided when creating the key mapping.

Translations can be added manually or using [data generation](./data-generation/translations).

```json
{
  "key.category.example-mod.custom_category": "Example Mod Custom Category",
  "key.example-mod.send_to_chat": "Send to Chat"
}
```

![Translated Key Category and Mapping](/assets/develop/key-mappings/translated.png)

## Reacting to Key Mappings Outside of Screens {#reacting-to-key-mappings-outside-of-screens}

Now that we have a key mapping, we can react to it using a client tick event.

<<< @/reference/latest/src/client/java/com/example/docs/keymapping/ExampleModKeyMappingsClient.java#client_tick_event

This will print "Key Pressed!" to the in-game chat every time the mapped key is pressed. Keep in mind that holding the key will repeatedly print the message to the chat, so you might want to implement guards if this logic only needs to trigger once.

![Message in Chat](/assets/develop/key-mappings/key_mapping_pressed.png)

## Reacting to Key Mappings Inside of Screens {#reacting-to-key-mappings-on-active-screens}

As you can see in the previous example, we react to key mappings outside of screens. However, we can also react to key mappings inside of screens.

<<< @/reference/latest/src/client/java/com/example/docs/keymapping/ExampleModKeyMappingsClient.java#screen_before_init_event

This checks if the current screen is the `TitleScreen` or `CreativeModeInventoryScreen`. If it is, pressing the mapped key while outside a world (when no player entity exists) will log "The key was pressed!", or if pressed inside a world, it will close the screen and send "Key Pressed! Closing screen" to the in-game chat.

Note that the `InventoryScreen` swaps out for `CreativeModeInventoryScreen` when in creative mode, so the check should be done for both screens if you want to react to the key mapping in both survival and creative mode.

::: tip

Though not recommended, you can remove the `screen instanceof` check to hook the event listener to all screens. This will allow you to react to key mappings in any screen.

:::

<VideoPlayer src="/assets/develop/key-mappings/in_screen_key_map.webm">Close and print message</VideoPlayer>
