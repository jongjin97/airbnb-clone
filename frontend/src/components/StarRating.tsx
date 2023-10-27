import { useState } from 'react';
import { FaStar } from 'react-icons/fa';
import { useController, Control, FieldValues, UseFormRegister } from 'react-hook-form';
import { control } from 'leaflet';

interface StarRatingProps {
  name: string;
  register: UseFormRegister<FieldValues>;
}

const StarRating: React.FC<StarRatingProps> = ({ name, register }) => {
  const [rating, setRating] = useState(0);

  const handleRating = (value: number) => {
    setRating(value);
  };

  return (
    <div className="flex items-center">
      {[1, 2, 3, 4, 5].map((value) => (
        <label key={value} className="cursor-pointer">
          <input
            type="radio"
            {...register("rating")}
            name={name}
            value={value}
            checked={rating === value}
            onChange={() => handleRating(value)}
            className="sr-only"
          />
          <FaStar
            className={`text-2xl ${
              value <= rating ? 'text-yellow-400' : 'text-gray-400'
            }`}
          />
        </label>
      ))}
    </div>
  );
};

export default StarRating;
