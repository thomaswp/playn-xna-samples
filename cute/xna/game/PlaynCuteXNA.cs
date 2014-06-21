using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using playn.core;
using PlayNXNA;

namespace PlaynCute
{
    public class PlaynCuteXNA : XNAGame
    {
        protected override void Initialize()
        {
            base.Initialize();

            Importer.Import();
            Game game = new playn.sample.cute.core.CuteGame();
            PlayN.run(game);
        }

        protected override XNAPlatform registerPlatform()
        {
            return XNAPlatform.register();
        }

        static void Main(string[] args)
        {
            using (PlaynCuteXNA game = new PlaynCuteXNA())
            {
                game.Run();
            }
        }
    }
}
