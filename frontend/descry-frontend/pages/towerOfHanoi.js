import styles from '@/styles/algorithm.module.css'
import Head from "next/head";
import Launch from "@/components/launch";

export default function Home() {
    return (
        <>
            <Head>
                <title>Descry - Tower of Hanoi</title>
                <meta name="viewport" content="width=device-width, initial-scale=1"/>
                <link rel="icon" type='image/x-icon' href='../public/favicon.ico'/>
            </Head>

            <div className={styles.main}>
                <div className={styles.title}>
                    <h1>Tower of Hanoi</h1>
                </div>
                <ul className={styles.description}>
                    <li>
                        <strong>Tower of Hanoi</strong> is a puzzle involving <strong>three vertical pegs</strong> and a set of different sized disks.
                    </li>
                    <li>
                        The <strong>goal</strong> of the puzzle is to <strong>transfer the stack of disks from one peg to another</strong>, such that disks are <strong>moved individually</strong> and <strong>no bigger disk are placed on a smaller disk</strong>.
                    </li>
                </ul>

                <Launch file={"/TowerOfHanoi.jar"}/>
            </div>

        </>
    );
}