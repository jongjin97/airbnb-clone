import { GoogleLogin } from "@react-oauth/google";

const GoogleOAuth = () => {
    const responseMessage = (response: any) => {
        console.log(response);
    };
    const errorMessage = (error: any) => {
        console.log(error);
    };
    return (
        <div>
            <GoogleLogin onSuccess={responseMessage}/>
        </div>
    )
}

export default GoogleOAuth;