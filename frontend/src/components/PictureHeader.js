import { CarouselProvider, Slide, Slider } from "pure-react-carousel";
import "pure-react-carousel/dist/react-carousel.es.css";
import React from "react";
function PictureHeader(props) {
  return (
    <div className="slideshow-container">
      <CarouselProvider
        naturalSlideWidth={100}
        naturalSlideHeight={125}
        totalSlides={3}
        isPlaying={true}
      >
        <Slider>
          <Slide index={0}>
            <img className="carousel-image" src="/images/image-1.jpg" />
          </Slide>
          <Slide index={1}>
            <img className="carousel-image" src="/images/image-2.jpg" />
          </Slide>
          <Slide index={2}>
            <img className="carousel-image" src="/images/image-3.jpg" />
          </Slide>
        </Slider>
      </CarouselProvider>
    </div>
  );
}

export default PictureHeader;
