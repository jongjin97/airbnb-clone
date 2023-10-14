import { Container } from './Container'
import Logo from './navbar/Logo'

const Navbar = () => {
    return (
        <div className='fixed w-full bg-white z-10 shadow-sm'>
            <div className='py-4
                border-b-[1px]'>
                  <Container/>
                  <Logo/>
            </div>
        </div>
    )
}