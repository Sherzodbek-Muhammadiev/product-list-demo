package uz.gita.task.util

import uz.gita.task.data.model.ProductData

object PreviewUtil {
    val testProductData = ProductData(
        name = "Product name",
        imageUrl = "url",
        price = "12$"
    )

    val testDescription = """
        Android is a mobile operating system based on a modified version of the Linux kernel and other open-source software, designed primarily for touchscreen mobile devices such as smartphones and tablets. Android is developed by a consortium of developers known as the Open Handset Alliance, though its most widely used version is primarily developed by Google. It was unveiled in November 2007, with the first commercial Android device, the HTC Dream, being launched in September 2008.

        At its core, the operating system is known as the Android Open Source Project (AOSP)[5] and is free and open-source software (FOSS) primarily licensed under the Apache License. However, most devices run on the proprietary Android version developed by Google, which ships with additional proprietary closed-source software pre-installed,[6] most notably Google Mobile Services (GMS)[7] which includes core apps such as Google Chrome, the digital distribution platform Google Play, and the associated Google Play Services development platform. Firebase Cloud Messaging is used for push notifications. While AOSP is free, the "Android" name and logo are trademarks of Google, which imposes standards to restrict the use of Android branding by "uncertified" devices outside their ecosystem.[8][9]

        Over 70 percent of smartphones based on the Android Open Source Project run Google's ecosystem (which is known simply as Android), some with vendor-customized user interfaces and software suites, such as TouchWiz and later One UI by Samsung and HTC Sense.[10] Competing ecosystems and forks of AOSP include Fire OS (developed by Amazon), ColorOS by Oppo, OriginOS by Vivo, MagicUI by Honor, or custom ROMs such as LineageOS.

        The source code has been used to develop variants of Android on a range of other electronics, such as game consoles, digital cameras, portable media players, and PCs, each with a specialized user interface. Some well-known derivatives include Android TV for televisions and Wear OS for wearables, both developed by Google. Software packages on Android, which use the APK format, are generally distributed through proprietary application stores like Google Play Store, Amazon Appstore, Samsung Galaxy Store, Huawei AppGallery, Cafe Bazaar, GetJar, and Aptoide, or open source platforms like F-Droid.
    """.trimIndent()
}