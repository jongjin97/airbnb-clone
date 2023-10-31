import { CredentialResponse, GoogleLogin, useGoogleLogin } from "@react-oauth/google";
import axios from "axios";
import { decode } from "node:punycode";

const GoogleOAuth = () => {
    const handleGoogleLogin2 = async () => {
      const authUrl = `http://localhost:8080/oauth2/authorization/google?redirect_uri=http://localhost:3000`;
      try {
        // 구글 로그인 URL로 리다이렉트
        window.location.href = authUrl;
      } catch (error) {
        console.error("구글 로그인 에러:", error);
      }
    };

    const sucess = (data: any) => {
      console.log(data);
    }
    return (
        <div className="w-full">
            <button onClick={handleGoogleLogin2}>구글로그인</button>
            <GoogleLogin onSuccess={sucess} hosted_domain="localhost:8080"/>
        </div>
    )
}

export default GoogleOAuth;

// 랜덤한 문자열 생성 함수
const generateRandomString = () => {
  let randomString = "";
  const characters =
   "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
  for (let i = 0; i < 16; i++) {
   randomString += characters.charAt(
     Math.floor(Math.random() * characters.length)
   );
 }
 return randomString;
};