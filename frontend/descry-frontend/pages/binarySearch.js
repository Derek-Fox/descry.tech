import styles from '@/styles/algorithm.module.css'
import Head from "next/head";
import Launch from "@/components/launch";

export default function Home() {
    return (
        <>
            <Head>
                <title>Descry - Binary Search</title>
                <meta name="viewport" content="width=device-width, initial-scale=1"/>
                <link rel="icon" type='image/x-icon' href='../public/favicon.ico'/>
            </Head>

            <div className={styles.main}>
                <div className={styles.title}>
                    <h1>Binary Search</h1>
                </div>
                <ul className={styles.description}>
                    <li>
                        <strong>Binary Search</strong> is a searching algorithm used to find an element's position in a
                        sorted array.
                    </li>
                    <li>
                        The search can be performed <strong>iteratively</strong> or <strong>recursively</strong>.
                    </li>
                    <li>
                        Essentially, binary
                        search defines a <strong>high</strong> and a <strong>low</strong> pointer that work together to
                        "single out" the target value.
                    </li>
                </ul>

                <Launch file={"/binary-search.jar"}></Launch>
            </div>

        </>
    );
}