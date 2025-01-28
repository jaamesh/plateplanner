import { useLocation } from "react-router-dom";
import image1 from "../assets/avocado.png";
import image2 from "../assets/breakfast.png";
import image3 from "../assets/eating.png";
import image4 from "../assets/healthy-food.png";
import image5 from "../assets/salad.png";
import image6 from "../assets/strawberry-juice.png";

const images = [image1, image2, image3, image4, image5, image6];

const RandomImage = () => {
  const location = useLocation();
  const randomIndex = Math.floor(Math.random() * images.length);

  return (
    <div>
      <img
        src={images[randomIndex]}
        alt="Illustration"
        style={{ width: "200px", height: "auto" }}
      />
    </div>
  );
};

export default RandomImage;
