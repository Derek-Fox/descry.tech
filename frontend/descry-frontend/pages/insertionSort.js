import styles from '@/styles/algorithm.module.css'
import Head from "next/head";
import Launch from "@/components/launch";

export default function Home() {
    return (
        <>
            <Head>
                <title>Descry - Insertion Sort</title>
                <meta name="viewport" content="width=device-width, initial-scale=1"/>
                <link rel="icon" type='image/x-icon' href='../public/favicon.ico'/>
            </Head>

            <div className={styles.main}>
                <div className={styles.title}>
                    <h1>Insertion Sort</h1>
                </div>
                <ul className={styles.description}>
                    <li>
                        <strong>Insertion Sort</strong> is a sorting algorithm that places <strong>unsorted elements in their place each iteration</strong>.
                    </li>
                    <li>
                        Insertion sort is similar to how people sort cards.
                    </li>
                    <li>
                        This sort is performed by starting with the <strong>first element</strong> and sorting down all the way to the <strong>last element</strong>.
                    </li>
                </ul>

                <Launch file="/InsertionSort.jar"></Launch>
            </div>

        </>
    );
}