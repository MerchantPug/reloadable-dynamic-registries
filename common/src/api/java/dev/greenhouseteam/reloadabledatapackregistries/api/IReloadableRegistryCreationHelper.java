package dev.greenhouseteam.reloadabledatapackregistries.api;

import com.mojang.serialization.Codec;
import dev.greenhouseteam.reloadabledatapackregistries.api.loader.CustomDataLoader;
import dev.greenhouseteam.reloadabledatapackregistries.api.platform.IRDRApiPlatformHelper;
import dev.greenhouseteam.reloadabledatapackregistries.api.platform.ServiceUtil;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

// TODO: Document this
public interface IReloadableRegistryCreationHelper {

    IReloadableRegistryCreationHelper INSTANCE = ServiceUtil.load(IReloadableRegistryCreationHelper.class);

    default <T> void fromExistingRegistry(ResourceKey<Registry<T>> registryKey) {
        IRDRApiPlatformHelper.INSTANCE.fromExistingRegistry(this, registryKey);
    }

    default <T> void registerReloadableRegistry(ResourceKey<Registry<T>> registryKey, Codec<T> codec) {
        registerNetworkableReloadableRegistry(registryKey, codec, null);
    }

    default <T> void registerNetworkableReloadableRegistry(ResourceKey<Registry<T>> registryKey, Codec<T> codec) {
        registerNetworkableReloadableRegistry(registryKey, codec, codec);
    }

    <T> void registerNetworkableReloadableRegistry(ResourceKey<Registry<T>> registryKey, Codec<T> codec, Codec<T> networKCodec);

    <T> void setCustomDataLoader(ResourceKey<Registry<T>> registryKey, CustomDataLoader<T> customDataLoader);
}
