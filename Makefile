gen1:
	confctl obfuscate \
    --configuration ./obfuscation-aes.yml \
    --output ./app/src/main/java/com/inteniquetic/kotlinobfuscation/example/config/ObfuscationConfiguration.kt
`
gen2:
	confctl obfuscate \
       --configuration ./obfuscation-chacha20.yml \
       --output ./app/src/main/java/com/inteniquetic/kotlinobfuscation/example/config/ObfuscationConfiguration2.kt
