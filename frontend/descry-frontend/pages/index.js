import Head from 'next/head'

import React, {useRef} from "react";


import Image from 'next/image'

import code1 from '../public/code1.svg'
import code2 from '../public/code2.svg'
import code3 from '../public/code3.svg'
import code4 from '../public/code4.svg'
import code5 from '../public/code5.svg'
import code6 from '../public/code6.svg'

import {Inter} from 'next/font/google'
import styles from '@/styles/Home.module.css'

const inter = Inter({subsets: ['latin']})

export default function Home() {
    return (
        <>
            <Head>
                <title>Descry - Algorithms Visualized</title>
                <meta name="description" content="A collection of intuitive visualizations of algorithms."/>
                <meta name="viewport" content="width=device-width, initial-scale=1"/>
                {/* <link rel="icon" href="/favicon.ico" /> */}
            </Head>
            <main className={styles.main}>
                <div className={styles.navbar}>
                    <div className={styles.title}>
                        <h1>Descry</h1>
                    </div>
                    <div className={styles.subtitle}>
                        <h2>Algorithms Visualized</h2>
                    </div>
                </div>

                <Algorithms/>

                <div className={styles.information}>
                    <div className={styles.infoBox}>
                        <div>
                            <h1>LEARN </h1>
                            <p>algorithms with <u>intuitive</u> and <u>interactive</u> examples.</p>
                        </div>

                        <div>
                            <Image
                                src={code1}
                                alt="Image of algorithm"
                                className={styles.infoBoxImg}
                            />
                        </div>
                    </div>
                    <div className={styles.infoBox}>
                        <div>
                            <Image
                                src={code2}
                                alt="Image of algorithm"
                                className={styles.infoBoxImg}
                            />
                        </div>

                        <div>
                            <h1>WRITE </h1>
                            <p>code to be <u>dynamically</u> <u>visualized</u> by our graphics engine.</p>
                        </div>
                    </div>
                </div>
            </main>
        </>
    )
}

function Algorithms() {
    return (
        <div className={styles.algorithms}>
            <div className={styles.algorithmBoxes}>
                <AlgorithmBox name={"Binary Search"} image={code3}/>
                <AlgorithmBox name={"Insertion Sort"} image={code4}/>
                <AlgorithmBox name={"Dijkstra's Algorithm"} image={code5}/>
                <AlgorithmBox name={"Depth First Search"} image={code6}/>
            </div>
        </div>

    );
}

function AlgorithmBox({name, image}) {
    return (
        <div className={styles.algorithmBox}>
            <Image
                src={image}
                alt="Image of algorithm"
                className={styles.algorithmBoxImg}
            />
            <h2>{name}</h2>
        </div>
    )
}
