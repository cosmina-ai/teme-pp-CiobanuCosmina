import asyncio
import random

async def suma(name, queue):
    while not queue.empty():
        n = await queue.get()
        print(f"{name} a preluat valoarea n={n} din coada.")
        total = 0
        for i in range(n + 1):
            total += i
            await asyncio.sleep(0.01)
        print(f"{name}: suma de la 0 la {n} este {total}")
        queue.task_done()

async def main():
    # coada
    q = asyncio.Queue()
    valori_n = [random.randint(10, 100) for _ in range(4)]
    for val in valori_n:
        await q.put(val)

    workers = [asyncio.create_task(suma(f"Corutina-{i+1}", q)) for i in range(4)]
    await asyncio.gather(*workers)

if __name__ == "__main__":
    asyncio.run(main())
