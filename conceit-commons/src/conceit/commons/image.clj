(ns conceit.commons.image
  (import [javax.imageio ImageIO ImageWriter ImageWriteParam IIOImage]
          [javax.imageio.stream ImageOutputStream]
          [java.awt.image BufferedImage ImageObserver]
          [java.awt Image Graphics2D RenderingHints]))

(defn ^BufferedImage buffered-image [source]
  (ImageIO/read source))

(defn scale [^Image source-image width height]
  (let [^BufferedImage scaled (BufferedImage. ^Integer width ^Integer height ^Integer BufferedImage/TYPE_INT_RGB)
        ^Graphics2D graphics (.createGraphics scaled)]
    (if (and (= width (.getWidth source-image))
             (= height (.getHeight source-image)))
      source-image
      (try (doto graphics
             (.setRenderingHints {RenderingHints/KEY_ALPHA_INTERPOLATION RenderingHints/VALUE_ALPHA_INTERPOLATION_QUALITY
                                  RenderingHints/KEY_ANTIALIASING RenderingHints/VALUE_ANTIALIAS_ON
                                  RenderingHints/KEY_COLOR_RENDERING RenderingHints/VALUE_COLOR_RENDER_QUALITY
                                  RenderingHints/KEY_DITHERING RenderingHints/VALUE_DITHER_ENABLE
                                  RenderingHints/KEY_RENDERING RenderingHints/VALUE_RENDER_QUALITY
                                  RenderingHints/KEY_INTERPOLATION RenderingHints/VALUE_INTERPOLATION_BILINEAR
                                  RenderingHints/KEY_FRACTIONALMETRICS RenderingHints/VALUE_FRACTIONALMETRICS_ON
                                  RenderingHints/KEY_STROKE_CONTROL RenderingHints/VALUE_STROKE_NORMALIZE})
             (.drawImage source-image 0 0 ^Integer width ^Integer height nil))
           scaled
           (finally (.dispose graphics))))))

(defn scale-for-width [^Image source-image width]
  (scale source-image
         width
         (let [fraction (/ width (.getWidth source-image))]
           (int (* fraction (.getHeight source-image))))))

(defn write-image! [^BufferedImage image format to & {:keys [quality]}]
  (let [writer ^ImageWriter (.next (ImageIO/getImageWritersByFormatName format))]
    (try (.setOutput writer ^ImageOutputStream (ImageIO/createImageOutputStream to))
         (let [parameter ^ImageWriteParam (.getDefaultWriteParam writer)]
           (when quality
             (doto parameter
               (.setCompressionMode ImageWriteParam/MODE_EXPLICIT)
               (.setCompressionQuality quality)))
           (.write writer nil (IIOImage. image nil nil) parameter)))))
