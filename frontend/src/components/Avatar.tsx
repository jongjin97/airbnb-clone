'use client';

interface AvatarProps {
  src: string | null | undefined;
  size?: number;
}

const Avatar: React.FC<AvatarProps> = ({ src, size }) => {
  return (
    <img
      className="rounded-full"
      height={size === undefined ? '30' : size}
      width={size === undefined ? '30' : size}
      alt="Avatar"
      src={src || '/placeholder.jpg'}
    />
  );
};

export default Avatar;
