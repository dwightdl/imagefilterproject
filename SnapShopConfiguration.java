/**
* Report
* I didn't add any additional filters (the required ones were enough blood sweat and
* tears). I found that I couldn't brute force my way through calculating the weighted 
* averages and applying the color values. Originally I had a different line of code for
* each index around a certain pixel and for each RGB value on that index. I also further
* cemented the idea that Java is not Python and I can't do things like data[-1][-1] to
* access what I want to. I could say that the value of nested for loops was the ultimate
* realization, but really it was going to the tutoring center and seeking help that made
* this project possible for me. I've learned that it is important to utilize whatever
* resources I can instead of staring at my code and hoping that ideas will come to me.
* -David
*/

/**
 * A class to configure the SnapShop application
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SnapShopConfiguration
{
  /**
   * Method to configure the SnapShop.  Call methods like addFilter
   * and setDefaultFilename here.
   * @param theShop A pointer to the application
   */
  public static void configure(SnapShop theShop)
  {

    theShop.setDefaultFilename("billg.jpg");
    theShop.addFilter(new FlipHorizontalFilter(), "Flip Horizontal");
		theShop.addFilter(new FlipVerticalFilter(), "Flip Vertical");
		theShop.addFilter(new NegativeFilter(), "Negative Filter");
		theShop.addFilter(new GaussianFilter(), "Gaussian Blur");
		theShop.addFilter(new LaplacianFilter(), "Laplacian Filter");
		theShop.addFilter(new UnsharpMaskingFilter(), "Unsharp Masking Filter");
		theShop.addFilter(new EdgyFilter(), "Edgy Filter");
    // add your other filters below
  }
}
