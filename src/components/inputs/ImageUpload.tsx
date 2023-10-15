import { useCallback } from "react";
import { TbPhotoPlus } from "react-icons/tb";

declare global {
    var cloudinary: any
  }
  
  const uploadPreset = "pgc9ehd5";
  
  interface ImageUploadProps {
    onChange: (value: string) => void;
    value: string;
  }
  
  const ImageUpload: React.FC<ImageUploadProps> = ({
    onChange,
    value
  }) => {
    const handleUpload = useCallback((e: React.ChangeEvent<HTMLInputElement>) => {
      const file = e.target.files?.[0];
      if (file) {
        const reader = new FileReader();
        reader.onloadend = () => {
          onChange(reader.result as string);
        };
        reader.readAsDataURL(file);
      }
    }, [onChange]);
  
          return (
            <div
              onClick={() => (document.getElementById('fileInput') as HTMLInputElement).click()}
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
              <TbPhotoPlus
                size={50}
              />
              <input
                type="file"
                accept="image/*"
                onChange={handleUpload}
                id="fileInput"
                style={{ display: 'none' }}
              />
              <div className="font-semibold text-lg">
                Click to upload
              </div>
              {value && (
                <div className="
                absolute inset-0 w-full h-full">
                  <img
                    //fill 
                    style={{ objectFit: 'cover' }} 
                    src={value} 
                    alt="House" 
                  />
                </div>
              )}
            </div>
          )
  }
  
  export default ImageUpload;