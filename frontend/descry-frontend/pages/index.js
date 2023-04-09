import Head from 'next/head'

import Image from 'next/image'
import code1 from '../public/code1.svg'
import code2 from '../public/code2.svg'
import code3 from '../public/code3.svg'
import code4 from '../public/code4.svg'
import code5 from '../public/code5.svg'
import code6 from '../public/code6.svg'
import code8 from '../public/code8.svg'
import code9 from '../public/code9.svg'

import {Inter} from 'next/font/google'
import styles from '@/styles/Home.module.css'

import Link from 'next/link'

const inter = Inter({subsets: ['latin']})

export default function Home() {
    return (
        <>
            <Head>
                <title>Descry - Algorithms Visualized</title>
                <meta name="description" content="A collection of intuitive visualizations of algorithms."/>
                <meta name="viewport" content="width=device-width, initial-scale=1"/>
                <link rel="icon" type='image/x-icon' href='../public/favicon.ico'/>
            </Head>
            <main className={styles.main}>

                <Algorithms/>

                <div className={styles.information} id="about">
                    <div className={styles.infoBox}>
                        <div>
                            <h1>LEARN </h1>
                            <p>algorithms with <u>intuitive</u> and <u>interactive</u> examples.</p>
                        </div>

                        <div>
                            <Image
                                src={code8}
                                alt="Image of algorithm"
                                className={styles.infoBoxImg}
                            />
                        </div>
                    </div>
                    <div className={styles.infoBox}>
                        <div>
                            <Image
                                src={code1}
                                alt="Image of algorithm"
                                className={styles.infoBoxImg}
                            />
                        </div>

                        <div>
                            <h1>WRITE </h1>
                            <p>code to be <u>dynamically</u> <u>visualized</u> by our graphics engine.</p>
                        </div>
                    </div>
                    <div className={styles.infoBox}>
                        <div>
                            <h1>TEACH </h1>
                            <p>students <u>complicated</u> algorithms with ease.</p>
                        </div>

                        <div>
                            <Image
                                src={code9}
                                alt="Image of algorithm"
                                className={styles.infoBoxImg}
                            />
                        </div>
                    </div>
                </div>

                <div className={styles.usage} id="usage">
                    <h1>
                        Usage
                    </h1>
                    <ol>
                        <li>
                            Select the algorithm you would like to visualize from the homepage.
                        </li>
                        <li>
                            Download the listed <strong>jar</strong> file.
                            (NOTE: You must have <strong>Java 19+</strong> installed!)
                        </li>
                        <li>
                            Execute the downloaded file and enjoy your visualization!
                        </li>
                    </ol>
                </div>
            </main>
        </>
    )
}

function Algorithms() {
    return (
        <div className={styles.algorithms}>
            <div className={styles.algorithmBoxes}>
                <AlgorithmBox name={"Binary Search"} image={code3} url={"binarySearch"}/>
                <AlgorithmBox name={"Insertion Sort"} image={code4} url={"insertionSort"}/>
                <AlgorithmBox name={"Tower of Hanoi"} image={code6} url={"towerOfHanoi"}/>
                <AlgorithmBox name={"Maze Generator"} image={code2} url={"mazeGenerator"}/>
                <AlgorithmBox name={"Maze Solver"} image={code5} url={"mazeSolver"}/>
            </div>
        </div>

    );
}

function AlgorithmBox({name, image, url}) {
    return (
        <Link href={url} className={styles.algorithmBox}>
            <Image
                src={image}
                alt="Image of algorithm"
                className={styles.algorithmBoxImg}
            />
            <h2>{name}</h2>
        </Link>
    )
}
