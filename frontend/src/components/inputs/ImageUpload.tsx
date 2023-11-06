import { useCallback, useState } from 'react';
import { AiOutlineArrowLeft, AiOutlineArrowRight } from 'react-icons/ai';
import { TbPhotoPlus } from 'react-icons/tb';

interface ImageUploadProps {
  onChange: (value: string[]) => void;
  value: string[];
}

const ImageUpload: React.FC<ImageUploadProps> = ({ onChange, value }) => {
  const handleUpload = useCallback(
    (e: React.ChangeEvent<HTMLInputElement>) => {
      const files = e.target.files;
      if (files) {
        const uploadedImages: string[] = [];
        const readerPromises: Promise<string>[] = [];

        for (let i = 0; i < files.length; i++) {
          const file = files[i];
          const readerPromise = new Promise<string>((resolve) => {
            const reader = new FileReader();
            reader.onloadend = () => resolve(reader.result as string);
            reader.readAsDataURL(file);
          });
          readerPromises.push(readerPromise);
        }

        Promise.all(readerPromises).then((results) => {
          results.forEach((result) => uploadedImages.push(result));
          onChange(uploadedImages);
        });
      }
      // const file = e.target.files?.[0];
      // if (file) {
      //   const reader = new FileReader();
      //   reader.onloadend = () => {
      //     onChange(reader.result as string);
      //   };
      //   reader.readAsDataURL(file);
      // }
    },
    [onChange]
  );
  const [currentImageIndex, setCurrentImageIndex] = useState(0);
  const handlePreviousImage = useCallback(() => {
    setCurrentImageIndex((prevIndex) =>
      prevIndex === 0 ? value.length - 1 : prevIndex - 1
    );
  }, [value]);

  const handleNextImage = useCallback(() => {
    setCurrentImageIndex((prevIndex) =>
      prevIndex === value.length - 1 ? 0 : prevIndex + 1
    );
  }, [value]);

  return (
    <>
      <div
        onClick={() =>
          (document.getElementById('fileInput') as HTMLInputElement).click()
        }
        className="
                relative
                cursor-pointer
                hover:opacity-70
                transition
                border-dashed 
                border-2 
                p-20 
                border-neutral-300
                flex
                flex-col
                justify-center
                items-center
                gap-4
                text-neutral-600
              "
      >
        <TbPhotoPlus size={50} />
        <input
          type="file"
          accept="image/*"
          onChange={handleUpload}
          id="fileInput"
          style={{ display: 'none' }}
        />
        <div className="font-semibold text-lg">Click to upload</div>
        {value && (
          <div
            className="
                absolute inset-0 w-full h-full"
          >
            <img
              style={{ objectFit: 'cover', width: '100%', height: '100%' }}
              src={value[currentImageIndex]}
              alt={`House ${currentImageIndex + 1}`}
            />
          </div>
        )}
      </div>
      <div
        className="
              flex 
              justify-between
              
            "
      >
        {value.length > 1 && (
          <>
            <AiOutlineArrowLeft size={20} onClick={handlePreviousImage} />
            <AiOutlineArrowRight size={20} onClick={handleNextImage} />
          </>
        )}
      </div>
    </>
  );
};

export default ImageUpload;
