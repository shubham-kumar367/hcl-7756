import { useState } from 'react'
import heroImg from './assets/hero.png'
import reactLogo from './assets/react.svg'
import viteLogo from './assets/vite.svg'
import './App.css'
import SampleCode from './component/SampleCode'
import UseFetchApi from './component/UseFetchApi'

function App()
{
   

    return (
        <div>
           {/* <SampleCode/>  */}
           <UseFetchApi />
        </div>
    )
}

export default App