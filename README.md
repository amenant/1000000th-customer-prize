http://geeks.redmart.com/2015/10/26/1000000th-customer-prize-another-programming-challenge/

The key to resolve this quizz is to use the KnapSack algorithm.

Don't forget before to remove products that doesn't fit the tote and sort by weight, then if 2 products have the same price, lighter products will come first.

As usual, TDD helps you solving this with baby steps.

If you want to run this, you might need to increase your JVM parameters as we are creating a big array (-Xms2048M -Xmx4064M)
