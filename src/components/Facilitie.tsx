import { GiShower, GiSkier, GiWifiRouter } from 'react-icons/gi';
import { MdOutdoorGrill, MdOutlineFireplace, MdTv } from 'react-icons/md';
import { FaHotTub, FaParking, FaSwimmingPool, FaUmbrellaBeach } from 'react-icons/fa';
import { FaKitchenSet } from 'react-icons/fa6';
import { MdLocalParking, MdAcUnit, MdWork } from 'react-icons/md';
import { RiBilliardsLine } from 'react-icons/ri';
import {CgGym} from 'react-icons/cg';
import {
  GiBarbecue,
  GiFireplace,
  GiPianoKeys,
  GiWashingMachine
} from 'react-icons/gi';
import Container from './Container';
import CategoryBox from './CategoryBox';

  export const facilities = [
    {
        label: "Wireless Internet",
        icon:GiWifiRouter ,
        description:"This property offers wireless internet access."
     },
     {
        label:"TV",
        icon:MdTv ,
        description:"This property has a television."
     },
     {
        label:"Kitchen",
       icon:FaKitchenSet ,
       description:"This property has a kitchen available for guest use."
     },
     {
       label:"Washing Machine",
       icon: GiWashingMachine ,
       description:"This property provides a washing machine for guest use."
     },
     {
       label:"Free Parking on Premises",
       icon:FaParking ,
       description:"Guests can park their vehicles for free on the premises of this property."
     }, 
     {
      label: "Paid Parking on Premises",
      icon: MdLocalParking ,
      description: "Paid parking options are available on the premises of this property."
  },
  {
      label: "Air Conditioning",
      icon: MdAcUnit ,
      description: "This property is equipped with air conditioning for guest comfort."
  },
  {
      label: "Dedicated Workspace",
      icon: MdWork ,
      description: "This property provides a dedicated workspace or office area for guests to work in."
  },
  {
  label : "Swimming Pool" , 
  icon : FaSwimmingPool, 
  description :"This Property has Swimming Pool"
  },
  {
  label : "Hot Tub" , 
  icon : FaHotTub, 
  description :"This Property has Hot Tub"
  },
  {
  label : "Barbecue Grill" , 
  icon :GiBarbecue, 
  description :"Barbecue Grill is available at this Property"
  },
  {
  label : "Outdoor Dining Area" , 
  icon :MdOutdoorGrill, 
  description :"Outdoor Dining Area is available at this Property"
  },
  {
  label : "Fireplace" , 
  icon :GiFireplace, 
  description :"Fireplace is present at this Property"
  },
  {
  label :"Billiards Table",
  icon:RiBilliardsLine ,
  description :"Billiards Table is present at this Property"
  },
  {
  label :"Indoor Fireplace",
  icon:MdOutlineFireplace ,
  description :"Indoor Fireplace is present at this Property"
  },
  {
  label :"Piano",
  icon:GiPianoKeys ,
  description :"Piano is present at this Property"
  },
  {
  label :"Gym Equipment",
  icon:CgGym ,
  description :"Gym Equipment is available at this Property"
  },
  {
      label:'Nearby Beach',
      icon:FaUmbrellaBeach ,
      description:'Property located near the beach'
      
  },
  {

      label:'Ski-in/Ski-out',
      icon:GiSkier ,
      description:'Property allows skiing access' 
  },
  {

      label:'Outdoor Shower Facilities',
      icon:GiShower ,
      description:'Outdoor Shower Facilities are provided'
  }
  ]

  const Facilities = () => {
    // const params = useSearchParams();
    // const category = params?.get('category');
    // const pathname = usePathname();
    // const isMainPage = pathname === '/';
  
    // if (!isMainPage) {
    //   return null;
    // }
  
    return (
      <Container>
        <div
          className="
            pt-4
            flex 
            flex-row 
            items-center 
            justify-between
            overflow-x-auto
          "
        >
          {facilities.map((item) => (
            <CategoryBox 
              key={item.label}
              label={item.label}
              icon={item.icon}
              //selected={category === item.label}
              selected={false}
    
            />
          ))}
        </div>
      </Container>
    );
  }