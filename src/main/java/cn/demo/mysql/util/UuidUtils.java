package cn.demo.mysql.util;

import java.nio.ByteBuffer;
import java.util.UUID;

public class UuidUtils {
    public static byte[] newUUIDBytes() {
        UUID u = UUID.randomUUID();
        byte[] uBytes = UuidUtils.asBytes(u);
        return uBytes;
    }

    public static String newUUIDString() {
        UUID u = UUID.randomUUID();
        return u.toString();
    }

    public static String asUuidString(byte[] bytes) {
        ByteBuffer bb = ByteBuffer.wrap(bytes);
        long firstLong = bb.getLong();
        long secondLong = bb.getLong();
        return new UUID(firstLong, secondLong).toString();
    }

    public static byte[] asBytes(String uuid) {
        return UuidUtils.asBytes(UUID.fromString(uuid));
    }

    public static UUID asUuid(String uuid) {
        return UUID.fromString(uuid);
    }

    private static UUID asUuid(byte[] bytes) {
        ByteBuffer bb = ByteBuffer.wrap(bytes);
        long firstLong = bb.getLong();
        long secondLong = bb.getLong();
        return new UUID(firstLong, secondLong);
    }

    private static byte[] asBytes(UUID uuid) {
        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());
        return bb.array();
    }

    private static void verifyUUIDBytesCanBeReconstructedBackToOriginalUUID() {
        UUID u = UUID.randomUUID();
        byte[] uBytes = UuidUtils.asBytes(u);
        UUID u2 = UuidUtils.asUuid(uBytes);
        System.out.println(u);
        System.out.println(u2);
        System.out.println(u.equals(u2));
    }

    private static void verifyNameUUIDFromBytesMethodDoesNotRecreateOriginalUUID() {
        UUID u = UUID.randomUUID();
        byte[] uBytes = UuidUtils.asBytes(u);
        UUID u2 = UUID.nameUUIDFromBytes(uBytes);
        System.out.println(u);
        System.out.println(u2);
        System.out.println(u.equals(u2));
    }

    public static void main(String[] args) {
        // verifyUUIDBytesCanBeReconstructedBackToOriginalUUID();
        // verifyNameUUIDFromBytesMethodDoesNotRecreateOriginalUUID();

        String str = UuidUtils.newUUIDString();
        System.out.println(str);
        System.out.println(UUID.fromString(str));

    }
}