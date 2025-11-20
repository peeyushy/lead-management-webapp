package com.fhc.util;

import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECGenParameterSpec;
import java.util.Base64;

public class VapidKeyGenerator {

	public static void main(String[] args) throws Exception {
		// Generate EC key pair with secp256r1 curve
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
		ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256r1");
		keyGen.initialize(ecSpec);

		KeyPair keyPair = keyGen.generateKeyPair();

		// Extract uncompressed EC public key point as bytes
		ECPublicKey ecPublicKey = (ECPublicKey) keyPair.getPublic();
		byte[] x = toUnsignedBytes(ecPublicKey.getW().getAffineX());
		byte[] y = toUnsignedBytes(ecPublicKey.getW().getAffineY());

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos.write(0x04); // Uncompressed point indicator
		baos.write(x);
		baos.write(y);
		byte[] publicKeyBytes = baos.toByteArray();

		// Encode public key as URL-safe base64 without padding
		String publicKeyBase64Url = Base64.getUrlEncoder().withoutPadding().encodeToString(publicKeyBytes);

		// Encode private key as URL-safe base64 without padding (PKCS8 encoded)
		String privateKeyBase64Url = Base64.getUrlEncoder().withoutPadding()
				.encodeToString(keyPair.getPrivate().getEncoded());

		System.out.println("Public VAPID Key (for frontend):");
		System.out.println(publicKeyBase64Url);

		System.out.println("Private VAPID Key (for backend):");
		System.out.println(privateKeyBase64Url);
	}

	// Helper: convert BigInteger bytes to unsigned fixed length byte array (32
	// bytes)
	private static byte[] toUnsignedBytes(java.math.BigInteger value) {
		byte[] src = value.toByteArray();
		if (src.length == 32) {
			return src;
		} else if (src.length == 33 && src[0] == 0) {
			// Remove leading zero padding byte
			byte[] dest = new byte[32];
			System.arraycopy(src, 1, dest, 0, 32);
			return dest;
		} else if (src.length < 32) {
			// Prepend zeros if shorter
			byte[] dest = new byte[32];
			System.arraycopy(src, 0, dest, 32 - src.length, src.length);
			return dest;
		} else {
			throw new IllegalArgumentException("Unexpected length for EC key coordinate");
		}
	}
}
