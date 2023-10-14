const Logo = () => {

    return ( 
      <img
        // onClick={() => router("/")}
        className="hidden md:block cursor-pointer" 
        src = '/logo.png'
        height="100" 
        width="100" 
        alt="Logo" 
      />
     );
  }
   
  export default Logo;