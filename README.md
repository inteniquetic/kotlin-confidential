# kotlin-obfuscation

Kotlin literal obfuscation against static reverse engineering. Inspiration [swift-confidential](https://github.com/securevale/swift-confidential)

[![](https://jitpack.io/v/inteniquetic/kotlin-obfuscation.svg)](https://jitpack.io/#inteniquetic/kotlin-obfuscation)

Example

```yaml
package: com.example.obfuscation
visibility: internal
defaultNamespace: create Obfuscation

algorithm:
  - xor using prng
  - split
  - rotate left 3
  - encrypt using aes-256-gcm
  # or: encrypt using chacha20-poly
  - shuffle

secrets:
  - name: apiKey
    value: "1234"
  - name: blacklist
    value:
      - List1
      - List2
      - List3
```

## Algorithm

The algorithm list defines a forward pipeline of transforms. The runtime reverses the pipeline in reverse order using TLV metadata emitted at obfuscation time.

Supported steps:

- `xor using prng` (seeded XOR stream)
- `split` (interleave halves)
- `rotate left N` or `rotate right N` (bit rotation per byte, N = 0..7)
- `encrypt using aes-256-gcm`
- `encrypt using chacha20-poly` (ChaCha20-Poly1305)
- `shuffle` (Fisher-Yates with seeded PRNG)

Notes:
- AES and ChaCha steps embed a nonce and tag in metadata; the key is derived from embedded k0/k1 via SHA-256.
- Order matters; choose stronger steps (AES/ChaCha, shuffle) later in the list.

## Secrets

Each entry in `secrets` produces a static accessor in the generated file.

Types:
- `value: "string"` generates `fun name(): String` (Kotlin) / `static func name() -> String` (Swift)
- `value: [ ... ]` generates `fun name(): List<String>` (Kotlin) / `static func name() -> [String]` (Swift)

Example:

```yaml
secrets:
  - name: apiKey
    value: "1234"
  - name: blacklist
    value:
      - List1
      - List2
      - List3
```

## Run

```shell
confctl obfuscate \
  --configuration ./obfuscation.yml \
  --output ./app/build/generated/source/kobfuscation/Obfuscation.kt \
  --lang kotlin
```

## Swift

Generate Swift output (requires CryptoKit on iOS/macOS):

```shell
confctl obfuscate \
  --configuration ./obfuscation.yml \
  --output ./Generated/Obfuscation.swift \
  --lang swift
```
