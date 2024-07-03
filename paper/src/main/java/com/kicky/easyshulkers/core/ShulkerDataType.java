package com.kicky.easyshulkers.core;

import com.kicky.easyshulkers.Shulker;
import org.apache.commons.lang.SerializationUtils;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;


public class ShulkerDataType implements PersistentDataType<byte[], Shulker> {

    @Override
    public @NotNull Class<Shulker> getComplexType() {
        return Shulker.class;
    }

    @Override
    public @NotNull Class<byte[]> getPrimitiveType() {
        return byte[].class;
    }

    @Override
    public byte @NotNull [] toPrimitive(@NotNull Shulker complex, @NotNull PersistentDataAdapterContext arg1) {
        return SerializationUtils.serialize(complex);
    }

    @Override
    public @NotNull Shulker fromPrimitive(byte @NotNull [] primitive, @NotNull PersistentDataAdapterContext arg1) {
        try {
            InputStream is = new ByteArrayInputStream(primitive);
            ObjectInputStream o = new ObjectInputStream(is);
            return (Shulker) o.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
